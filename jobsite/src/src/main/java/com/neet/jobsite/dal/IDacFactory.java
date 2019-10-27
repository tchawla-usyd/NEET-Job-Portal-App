package com.neet.jobsite.dal;

public interface IDacFactory {

	<T> T GetDac(DacType dacType) throws Exception;
}
