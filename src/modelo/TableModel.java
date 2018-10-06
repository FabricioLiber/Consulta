package modelo;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel{
	
	private Object[][] dados;
	private String[] colunas;
	private int posicao;
	
	public TableModel (int linha, String[] nomeColunas) {
		dados = new Object[linha][nomeColunas.length];
		colunas = nomeColunas;
	}
	
	@Override
	public String getColumnName(int column) {	
		return colunas[column];		
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.length;
	}

	@Override
	public Object getValueAt (int linha, int coluna) {
		return dados[linha][coluna];
	}
	
	public void addRow (Object[] dado) {
		for (int i = 0; i < colunas.length; i++) {
			this.dados[posicao][i] = dado[i];	
		}
		posicao ++;
		this.fireTableDataChanged();
	}

}