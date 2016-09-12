package com.mizore.gwt.gwtshowcase.client.screen.graph.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.mizore.gwt.gwtshowcase.client.screen.graph.interfaces.GraphDisplay;

import fr.vekia.vkgraph.client.charts.LineChart;
import fr.vekia.vkgraph.client.options.ChartOption;

public class GraphViewImpl implements GraphDisplay {

	private TextArea textArea;

	private LayoutPanel panel;

	private LineChart<List<Integer>> lineChart;

	public GraphViewImpl() {
		this.panel = new LayoutPanel();
		this.textArea = new TextArea();
		this.lineChart = new LineChart<List<Integer>>();

		
		List<List<Integer>> datasAll = new ArrayList<List<Integer>>();
		ArrayList<Integer> datas = new ArrayList<Integer>();
		datas.add(1);
		datas.add(3);
		datas.add(3);
		datas.add(2);
		datas.add(6);
		datas.add(3);
		datas.add(7);
		datasAll.add(datas);
		
		this.lineChart.setDatas(datasAll);
		this.lineChart.setBooleanOption(ChartOption.animate, true);
		
		this.panel.add(this.lineChart);
		this.panel.setWidgetTopHeight(this.lineChart, 0, Unit.PX, 70, Unit.PCT);
		this.panel.setWidgetLeftRight(this.lineChart, 0, Unit.PX, 0, Unit.PX);

		this.panel.add(this.textArea);
		this.panel.setWidgetTopBottom(this.textArea, 70, Unit.PCT, 0, Unit.PX);
		this.panel.setWidgetLeftRight(this.textArea, 0, Unit.PX, 0, Unit.PX);
		
		this.textArea.setWidth("100%");
		this.panel.setSize("100%", "100%");
	}

	@Override
	public Widget asWidget() {
		return this.panel;
	}

}
