package main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ListeUtilisateurs implements Serializable {

	private static final long serialVersionUID = -6447914795234124870L;

	protected Map<String, String> comptes = new HashMap<String, String>(); // (pseudo, mdp)
	protected static Map<String, Boolean> estAdmin = new HashMap<String, Boolean>(); // (pseudo, estAdmin)

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
		estAdmin.put("prof", true);
		estAdmin.put("mez", true);
		estAdmin.put("alex", true);
		estAdmin.put("eloy", true);
		estAdmin.put("vince", true);
		estAdmin.put("newbie1", false);
		estAdmin.put("newbie2", false);
		estAdmin.put("newbie3", false);
		estAdmin.put("guest", false);
	}

	public static Map<String, Boolean> getAdmin() {
		return estAdmin;
	}

}
