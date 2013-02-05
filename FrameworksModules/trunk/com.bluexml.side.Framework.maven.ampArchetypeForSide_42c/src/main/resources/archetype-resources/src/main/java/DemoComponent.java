#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package};

import java.util.logging.Logger;

import org.alfresco.repo.module.AbstractModuleComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A basic component that will be started for this module.
 * 
 */
public class DemoComponent extends AbstractModuleComponent
{
	Log log = LogFactory.getLog(DemoComponent.class);
	
    @Override
    protected void executeInternal() throws Throwable
    {
        System.out.println("DemoComponent has been executed");
        log.debug("Test debug logging is working");
        log.info("This should not be outputted by default");
    }
}
