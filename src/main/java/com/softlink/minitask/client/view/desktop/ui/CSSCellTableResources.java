package com.softlink.minitask.client.view.desktop.ui;

import com.google.gwt.user.cellview.client.CellTable;

public interface CSSCellTableResources extends CellTable.Resources {
	@Source({ CellTable.Style.DEFAULT_CSS, "celltable.css" })
	TableStyle cellTableStyle();

	interface TableStyle extends CellTable.Style {

	}

}
