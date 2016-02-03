package package_v2;

public class NetworkCard {
		private String constr;
		private String idcard;
		private String devicename;
		
		public NetworkCard(String devicename,String idcard){
			this.devicename = devicename;
			this.idcard = idcard;
			this.constr= "";
		}
		
		public String getConstr() {
			return constr;
		}
		public void setConstr(String constr) {
			this.constr = constr;
		}
		public String getIdcard() {
			return idcard;
		}
		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getDevicename() {
			return devicename;
		}

		public void setDevicename(String devicename) {
			this.devicename = devicename;
		}
}
	