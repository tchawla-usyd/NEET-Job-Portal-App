
package com.neet.jobsite.response;

import java.util.List;

public class CandiateJobListResponse {
	private List<CandateJobRow> jobRows;

	public List<CandateJobRow> getJobRows() {
		return jobRows;
	}

	public void setJobRows(List<CandateJobRow> jobRows) {
		this.jobRows = jobRows;
	}
}
