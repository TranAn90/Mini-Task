package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.user.cellview.client.DataGrid;

public interface CssDataGridResources extends DataGrid.Resources {

	@Source({ DataGrid.Style.DEFAULT_CSS, "DataGrid.css" })
	CustomStyle dataGridStyle();

	interface CustomStyle extends DataGrid.Style {

	}
}
