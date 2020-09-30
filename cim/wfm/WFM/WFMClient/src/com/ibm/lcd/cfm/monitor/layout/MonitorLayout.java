package com.ibm.lcd.cfm.monitor.layout;

import java.util.ArrayList;
import java.util.HashMap;

public final class MonitorLayout {

	private ArrayList areas = new ArrayList();
	private ArrayList links = new ArrayList();
	private ArrayList images = new ArrayList();
	private ArrayList colors = new ArrayList();
	private HashMap areasMap = new HashMap();
	private HashMap imagesMap = new HashMap();
	private HashMap colorsMap = new HashMap();
	private HashMap states = new HashMap();

	public final void addArea(AreaLayer area) {
		areas.add(area);
		areasMap.put(area.getId(), area);
	}

	public final int getAreaCount() {
		return areas.size();
	}

	public final AreaLayer getArea(String id) {
		return (AreaLayer) areasMap.get(id);
	}

	public final AreaLayer getArea(int index) {

		return (AreaLayer) areas.get(index);
	}

	public final void addEquipmentState(EquipmentState state) {
		states.put(state.getId(), state);
	}

	public final int getEquipmentStateCount() {
		return states.size();
	}

	public final EquipmentState getEquipmentState(String id) {
		return (EquipmentState) states.get(id);
	}

	public final void addImageDefinition(ImageDefinition definition) {
		images.add(definition);
		imagesMap.put(definition.getId(), definition);
	}

	public final int getImageDefinitionCount() {
		return images.size();
	}

	public final ImageDefinition getImageDefinition(String id) {
		return (ImageDefinition) imagesMap.get(id);
	}

	public final ImageDefinition getImageDefinition(int index) {
		try {
			return (ImageDefinition) images.get(index);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public final void addColorDefinition(ColorDefinition definition) {
		colors.add(definition);
		colorsMap.put(definition.type + definition.getId(), definition);
	}

	public final ColorDefinition getColorDefinition(int type, String id) {
		return (ColorDefinition) colorsMap.get(type + id);
	}
	
	/*
	public final ColorDefinition getColorDefinition(int type, String main, String sub) {
		for (int i = 0; i < colors.size(); i++) {
			try {
				if (((ColorDefinition) (colors.get(i))).getSubColorDefinition(type, main, sub)) {
					ColorDefinition colorDef = (ColorDefinition) colors.get(i);

					return (ColorDefinition) colorsMap.get(colorDef.type + colorDef.getId());
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				// System.out.println("MonitorLayout:getColorDefinition:["+
				// colors.get(i)+"]"+ e );
			}
		}

		return getColorDefinition(type, sub);
	}*/

	public final ColorDefinition[] getColorDefinition() {
		ColorDefinition[] array = new ColorDefinition[colors.size()];
		return (ColorDefinition[]) colors.toArray(array);
	}

	public final void addLinkDefinition(LinkDefinition definition) {
		links.add(definition);
	}

	public final LinkDefinition[] getLinkDefinition(int type) {
		ArrayList list = new ArrayList();
		LinkDefinition definition = null;
		for (int i = 0, j = links.size(); i < j; i++) {
			definition = (LinkDefinition) links.get(i);
			if (definition.type == type)
				list.add(definition);
		}
		LinkDefinition[] array = new LinkDefinition[list.size()];
		return (LinkDefinition[]) list.toArray(array);
	}

	public final LinkDefinition[] getLinkDefinition(int type, int sub_type) {
		ArrayList list = new ArrayList();
		LinkDefinition definition = null;
		for (int i = 0, j = links.size(); i < j; i++) {
			definition = (LinkDefinition) links.get(i);
			if (sub_type == LayoutType.CLN) {
				if (definition.type == type || definition.type == sub_type)
					list.add(definition);
			} else {
				if (definition.type == type || definition.type == sub_type || definition.type == LayoutType.NOCLN)
					list.add(definition);
			}
		}
		LinkDefinition[] array = new LinkDefinition[list.size()];
		return (LinkDefinition[]) list.toArray(array);
	}
}
