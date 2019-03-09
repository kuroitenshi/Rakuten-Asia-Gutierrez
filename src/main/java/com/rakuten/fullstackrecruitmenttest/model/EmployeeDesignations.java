package com.rakuten.fullstackrecruitmenttest.model;

public class EmployeeDesignations {
	private EmployeeDesignations() {
	}

	private static final String DEVELOPER_TAG = "Developer";
	private static final String SENIORDEVELOPER_TAG = "Senior Developer";
	private static final String MANAGER_TAG = "Manager";
	private static final String TEAMLEAD_TAG = "Team Lead";
	private static final String VP_TAG = "VP";
	private static final String CEO_TAG = "CEO";

	public static String getDeveloperTag() {
		return DEVELOPER_TAG;
	}

	public static String getSeniordeveloperTag() {
		return SENIORDEVELOPER_TAG;
	}

	public static String getManagerTag() {
		return MANAGER_TAG;
	}

	public static String getTeamleadTag() {
		return TEAMLEAD_TAG;
	}

	public static String getVpTag() {
		return VP_TAG;
	}

	public static String getCeoTag() {
		return CEO_TAG;
	}
	

}