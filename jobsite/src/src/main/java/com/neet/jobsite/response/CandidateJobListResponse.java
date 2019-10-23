
package com.neet.jobsite.response;

import java.util.List;

public class CandidateJobListResponse {
	private List<CandidateJobRow> jobRows;

	public List<CandidateJobRow> getJobRows() {
		return jobRows;
	}

	public void setJobRows(List<CandidateJobRow> jobRows) {
		this.jobRows = jobRows;
	}
}
