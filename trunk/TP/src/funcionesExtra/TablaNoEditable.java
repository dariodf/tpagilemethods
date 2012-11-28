package funcionesExtra;

import javax.swing.table.DefaultTableModel;

public class TablaNoEditable  extends DefaultTableModel
{
	 public TablaNoEditable(Object[][] datos, String[] titulosColumnas) {
		super(datos, titulosColumnas);
	}

	public boolean isCellEditable (int row, int column)
	   {
	       return false;
	   }
}
