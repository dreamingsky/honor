package honor.common;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	
	private static final long serialVersionUID = 5736204285135571776L;
	private int pageSize = 15;//һҳ���ݵ�����
	private int currentPage=1;//��ǰҳ
	private int totalRows;//��������
	private int totalPages;//��ҳ��
	private boolean isFirstPage = false;//�Ƿ��ǵ�һҳtrue��false��
	private boolean isEndPage = false;//�Ƿ������һҳtrue��false��
	private int start=0;
	private int end=0;
	private List results;
	
	public Page(int totalCount,int pageNum){
		this.totalRows = totalCount;
		if(pageNum>1){
			this.pageSize = pageNum;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isFirstPage() {
		if(currentPage<=totalPages){
			isFirstPage = true;
		}
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isEndPage() {
		if(currentPage>=totalPages){
			isFirstPage  = true;
		}
		return isEndPage;
	}
	public void setEndPage(boolean isEndPage) {
		this.isEndPage = isEndPage;
	}
	public List getResults() {
		return results;
	}
	public void setResults(List results) {
		this.results = results;
	}
	public int getStart() {
		start = (currentPage-1)*pageSize;
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		if(currentPage*pageSize>this.totalRows){
			end = this.totalRows-1;
		}else{
			end = currentPage*pageSize-1;
		}
		
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	

}
