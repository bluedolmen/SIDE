/***
   RWM : Requirements Web Modeler
   Copyright (C) 2008 (BlueXML / IRCCyN)

   This program is free software; you can redistribute it and/or
   modify it under the terms of the GNU General Public License
   as published by the Free Software Foundation; either version 2
   of the License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
***/

draw2d.FlowMenu=function(/*:draw2d.Workflow*/ workflow)
{
  this.actionDelete = new draw2d.ButtonDelete(this);
  this.actionFront = new draw2d.ButtonMoveFront(this);
  this.actionBack = new draw2d.ButtonMoveBack(this);

  draw2d.ToolPalette.call(this);

  this.setDimension(20,60);
  this.setBackgroundColor(new  draw2d.Color(220,255,255));
  this.currentFigure = null;
  this.myworkflow = workflow;
  this.added = false;
  this.setDeleteable(false);
  this.setCanDrag(false);
  this.setResizeable(false);
  this.setSelectable(false);
  this.setBackgroundColor(null);
  this.setColor(null);
  this.scrollarea.style.borderBottom="0px";
  this.setZOrder(10000);

  this.actionDelete.setPosition(0,0);
  this.actionFront.setPosition(0,18);
  this.actionBack.setPosition(0,36);

  this.addChild(this.actionDelete);
  this.addChild(this.actionFront);
  this.addChild(this.actionBack);
}

/** base class of my example double click figure 
 * You can use circle, oval,.....too
 **/
draw2d.FlowMenu.prototype = new draw2d.ToolPalette;


/**
 * Reenable the setAlpha method. This has been disabled in the Window class.
 *
 **/
draw2d.FlowMenu.prototype.setAlpha=function(/*:float 0-1*/ percent)
{
   draw2d.Figure.prototype.setAlpha.call(this,percent);
}

/**
 * The FlowMenu has no title bar => return false.
 *
 * @returns Returns [true] if the window has a title bar
 * @type boolean
 **/
draw2d.FlowMenu.prototype.hasTitleBar=function()
{
  return false;
}

/**
 * Call back method of the framework if the selected object has been changed.
 *
 * @param {draw2d.Figure} figure the object which has been selected.
 **/
draw2d.FlowMenu.prototype.onSelectionChanged=function(/*:draw2d.Figure*/ figure)
{
  if(figure==this.currentFigure)
     return;

  if(this.added==true)
  {
     this.myworkflow.removeFigure(this);
     this.added=false;
  }

  if(figure!=null && this.added==false)
  {
     // The figure has been changed. Hide the FlowMenu. The addFigure(..) will increase the alpha 
     // with an internal timer. But only if the the smooth handling is enabled.
     //
     if(this.myworkflow.getEnableSmoothFigureHandling()==true)
         this.setAlpha(0.01);

     this.myworkflow.addFigure(this,100,100);
     this.added=true;
  }

  // deregister the moveListener from the old figure
  //
  if(this.currentFigure!=null)
  {
     this.currentFigure.detachMoveListener(this);
  }

  this.currentFigure = figure;
  // deregister the moveListener from the old figure
  //
  if(this.currentFigure!=null)
  {
     this.currentFigure.attachMoveListener(this);
     this.onOtherFigureMoved(this.currentFigure);
  }
}


draw2d.FlowMenu.prototype.setWorkflow= function( /*:draw2d.Workflow*/ workflow)
{
  // Call the Figure.setWorkflow(...) and NOT the ToolPalette!
  // Reson: the ToolPalette deregister the selectionListener from the workflow. But we need 
  // the selection listener event.
  draw2d.Figure.prototype.setWorkflow.call(this,workflow);
}


/**
 * Move the FlowMenu in synch with the corresponding figure.
 *
 * @param {draw2d.Figure} figure The figure which has changed its position
 * @private
 */
draw2d.FlowMenu.prototype.onOtherFigureMoved=function(/*:draw2d.Figure*/ figure)
{
  if (figure instanceof draw2d.Line) {
    var pos = figure.getStartPoint();
    this.setPosition(pos.x+7,pos.y-16);
  } else {
    var pos = figure.getPosition();
    this.setPosition(pos.x+figure.getWidth()+7,pos.y-16);
  }
}

