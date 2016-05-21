package labs.zubovich.calculator;

import labs.zubovich.ui.RowEditorType;

public enum RowParam {
	CoefNew("Коэфициент новизны", Double.class, new RowEditorType.NumberEditor()),
	Test("Test", Boolean.class, new RowEditorType.TextEditor());

	private String title;
	private Class<?> type;
	private RowEditorType editorType;

	RowParam(String title, Class<?> type) {
		this.title = title;
		this.type = type;
		this.editorType = null;
	}

	RowParam(String title, Class<?> type, RowEditorType editorType) {
		this.title = title;
		this.type = type;
		this.editorType = editorType;
	}

	public String getTitle() {
		return title;
	}

	public Class<?> getType() {
		return type;
	}

	public RowEditorType getEditorType() {
		return editorType;
	}
}

