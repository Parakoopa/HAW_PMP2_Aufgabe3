package kalender;

public enum WiederholungType {
	TAEGLICH("täglich") {

		public int inTagen() {
			return 1;
		}
	},
	WOECHENTLICH("wöchentlich") {

		public int inTagen() {
			return 7;
		}
	};

	private String name;

	private WiederholungType(String name) {
		this.name = name;
	}


	public String toString() {
		return name;
	}

	public abstract int inTagen();
};
