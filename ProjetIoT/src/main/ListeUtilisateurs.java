package main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ListeUtilisateurs implements Serializable {

	private static final long serialVersionUID = -6447914795234124870L;

	protected Map<String, String> comptes = new HashMap<String, String>(); // (pseudo, mdp)
	protected static LinkedList<String> estAdmin = new LinkedList<String>(); 

	public ListeUtilisateurs() {
		comptes.put("prof", "prof");
		comptes.put("mez", "khodja");
		comptes.put("alex", "le balanger");
		comptes.put("eloy", "martinez");
		comptes.put("vince", "dollo");
		comptes.put("newbie1", "newbie");
		comptes.put("newbie2", "newbie");
		comptes.put("newbie3", "newbie");
		comptes.put("guest", "guest");
		estAdmin.add("prof");
		estAdmin.add("mez");
		estAdmin.add("alex");
		estAdmin.add("eloy");
		estAdmin.add("vince");
	}

	public static LinkedList<String> getAdmin() {
		return estAdmin;
	}

}
