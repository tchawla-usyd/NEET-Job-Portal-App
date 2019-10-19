
package com.neet.jobsite.response;

import java.util.List;

public class CandidateJobListResponse {
	private List<CandateJobRow> jobRows;

	public List<CandateJobRow> getJobRows() {
		return jobRows;
	}

	public void setJobRows(List<CandateJobRow> jobRows) {
		this.jobRows = jobRows;
	}
}
