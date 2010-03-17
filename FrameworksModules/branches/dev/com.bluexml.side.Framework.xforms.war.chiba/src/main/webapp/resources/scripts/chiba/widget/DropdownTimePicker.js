dojo.provide("chiba.widget.DropdownTimePicker");

dojo.require("dojo.widget.DropdownTimePicker");

dojo.widget.defineWidget(
        "chiba.widget.DropdownTimePicker",
        dojo.widget.DropdownTimePicker,
{
    widgetType: "chiba:DropdownTimePicker",
    datatype:"time",
    id:"",

    fillInTemplate: function(args, frag) {
        dojo.debug("Date.fillInTemplate", this, arguments);
        chiba.widget.DropdownTimePicker.superclass.fillInTemplate.apply(this, arguments);        
        this.domNode.setAttribute("xfreadonly", this.xfreadonly);
        this.inputNode.setAttribute("class", "value");

        if (this.xfreadonly == true) {
            this.inputNode.setAttribute("disabled", "disabled");
        }
        else {
            this.inputNode.removeAttribute("disabled");
        }

    },

		getValue: function(){
			// summary: return current date in RFC 3339 format
			return this.inputNode.value; /*String*/
		},

    onIconClick: function(evt) {
        if (this.xfreadonly == true) {
            this.isEnabled = false;
        }
        else {
            this.isEnabled = true;
        }
        chiba.widget.DropdownTimePicker.superclass.onIconClick.apply(this, arguments);
    },

    updateReadonly: function(readonly) {
        this.xfreadonly = readonly;
        if (this.xfreadonly == true) {
            this.inputNode.setAttribute("disabled", "disabled");
            this.isEnabled = false;
        }
        else {
            this.isEnabled = true;
            this.inputNode.removeAttribute("disabled");
        }
    },
    
    onInputChange: function() {
        chiba.widget.DropdownTimePicker.superclass.onInputChange.call(this);
        var sessionKey = document.getElementById("chibaSessionKey").value;
        Flux.setXFormsValue(updateUI, this.id.substring(0, this.id.length - 6), this.getValue() + ":00", sessionKey);
        //summary: triggered when this.value is changed
    },

    onSetTime: function() {
        var oldTime = this.getValue();
        chiba.widget.DropdownTimePicker.superclass.onSetTime.call(this);
        var newTime = this.getValue();
        if (oldTime != newTime) {
            var sessionKey = document.getElementById("chibaSessionKey").value;
            Flux.setXFormsValue(updateUI, this.id.substring(0, this.id.length - 6), newTime + ":00", sessionKey);
        }
    }
},
        "html"
        );