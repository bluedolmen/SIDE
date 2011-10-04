package keyManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage : <hostID> <key>");
		} else {
			String matchID = args[0];
			String key = args[1];

			KeyInformation kinfo = new KeyInformation(matchID, key);
			System.out.println("Test Key :");
			System.out.println(" matchID:" + matchID);
			System.out.println(" key:" + key);
			System.out.println("Validity : " + kinfo.getValidity());
			System.out.println("Date : " + kinfo.getValidationDate());
		}

	}

}
