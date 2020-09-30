
package com.ibm.lcd.cfm.monitor.layout;

import java.awt.Shape;

public abstract class BaseLayer extends IdentifyObject {
    final String CLASS_NAME = "BaseLayer";

    public String imageId = null;
    public Shape shape = null;
    public int layoutType = 0;
    //public int subLayoutType = 0;
    
    private String m_mainLinkURI;
    private String m_linkTarget;

    protected BaseLayer(String id) {
        super(id);
    }

    public BaseLayer(IdentifyObject state) {
        super(state.getId());
    }

    public String getReferredValue(String referredName) {
        return null;
    }
    
    public void setMainLinkURI(String uri){
    	m_mainLinkURI = uri;
    }
    
    public String getMainLinkURI(){
    	return m_mainLinkURI;
    }
    
    public void setLinkTarget(String target)
    {
    	m_linkTarget = target;
    }
    
    public String getLinkTarget()
    {
    	if(m_linkTarget != null && m_linkTarget.length() > 0) 
    		return m_linkTarget;
    	
    	return "_blank";
    }

}
