package sch;

import java.util.ArrayList;
import java.util.List;

public class SCHModel {
	ArrayList<SCHEntry> entry_list;
	public SCHModel() {
		// TODO Auto-generated constructor stub
		entry_list = new ArrayList<SCHEntry>();
	}
	
	public void addEntry(SCHEntry entry1){
		entry_list.add(entry1);
	}
	
	public void removeEntry(){
	}
	
	public List<SCHEntry> getEntryListModel(){
		return entry_list;
	}

	public List<SCHEntry> getEntryList() {
		// TODO Auto-generated method stub
		return entry_list;
	}
}
