package com.shulnet.mailer.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("portals")
public class Portal {

	@Id
	private String _id;
	
	@Field("institution_name")
	private String institutionName;
	
	@Field("portal_settings")
	private List<Setting> portalSettings;
	
	public class Setting {
		
		@Field("option_name")
		public String optionName;
		
		@Field("option_value")
		public String optionValue;
	
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public List<Setting> getPortalSettings() {
		return portalSettings;
	}

	public void setPortalSettings(List<Setting> portalSettings) {
		this.portalSettings = portalSettings;
	}
}
