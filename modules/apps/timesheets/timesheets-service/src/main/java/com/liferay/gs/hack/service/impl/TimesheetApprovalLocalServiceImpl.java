/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gs.hack.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gs.hack.model.TimesheetApproval;
import com.liferay.gs.hack.service.base.TimesheetApprovalLocalServiceBaseImpl;

/**
 * The implementation of the timesheet approval local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.gs.hack.service.TimesheetApprovalLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TimesheetApprovalLocalServiceBaseImpl
 * @see com.liferay.gs.hack.service.TimesheetApprovalLocalServiceUtil
 */
@ProviderType
public class TimesheetApprovalLocalServiceImpl
	extends TimesheetApprovalLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.gs.hack.service.TimesheetApprovalLocalServiceUtil} to access the timesheet approval local service.
	 */

	public List<TimesheetApproval> getTimesheetApprovalByTimesheetId(long timesheetId) {
		return timesheetApprovalPersistence.fe
	}
}