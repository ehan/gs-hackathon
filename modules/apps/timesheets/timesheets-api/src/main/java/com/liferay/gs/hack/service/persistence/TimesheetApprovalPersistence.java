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

package com.liferay.gs.hack.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException;
import com.liferay.gs.hack.model.TimesheetApproval;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the timesheet approval service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gs.hack.service.persistence.impl.TimesheetApprovalPersistenceImpl
 * @see TimesheetApprovalUtil
 * @generated
 */
@ProviderType
public interface TimesheetApprovalPersistence extends BasePersistence<TimesheetApproval> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TimesheetApprovalUtil} to access the timesheet approval persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the timesheet approvals where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the timesheet approvals where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @return the range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the timesheet approvals where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns an ordered range of all the timesheet approvals where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns the timesheet approvals before and after the current timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param timesheetApprovalId the primary key of the current timesheet approval
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public TimesheetApproval[] findByUuid_PrevAndNext(
		long timesheetApprovalId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Removes all the timesheet approvals where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of timesheet approvals where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching timesheet approvals
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the timesheet approval where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTimesheetApprovalException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the timesheet approval where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the timesheet approval where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the timesheet approval where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the timesheet approval that was removed
	*/
	public TimesheetApproval removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the number of timesheet approvals where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching timesheet approvals
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @return the range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns an ordered range of all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns the timesheet approvals before and after the current timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param timesheetApprovalId the primary key of the current timesheet approval
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public TimesheetApproval[] findByUuid_C_PrevAndNext(
		long timesheetApprovalId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Removes all the timesheet approvals where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching timesheet approvals
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the timesheet approvals where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @return the matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByTimesheetId(long timesheetId);

	/**
	* Returns a range of all the timesheet approvals where timesheetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param timesheetId the timesheet ID
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @return the range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByTimesheetId(
		long timesheetId, int start, int end);

	/**
	* Returns an ordered range of all the timesheet approvals where timesheetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param timesheetId the timesheet ID
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByTimesheetId(
		long timesheetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns an ordered range of all the timesheet approvals where timesheetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param timesheetId the timesheet ID
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findByTimesheetId(
		long timesheetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByTimesheetId_First(long timesheetId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the first timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByTimesheetId_First(long timesheetId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns the last timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public TimesheetApproval findByTimesheetId_Last(long timesheetId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the last timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public TimesheetApproval fetchByTimesheetId_Last(long timesheetId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns the timesheet approvals before and after the current timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetApprovalId the primary key of the current timesheet approval
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public TimesheetApproval[] findByTimesheetId_PrevAndNext(
		long timesheetApprovalId, long timesheetId,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator)
		throws NoSuchTimesheetApprovalException;

	/**
	* Removes all the timesheet approvals where timesheetId = &#63; from the database.
	*
	* @param timesheetId the timesheet ID
	*/
	public void removeByTimesheetId(long timesheetId);

	/**
	* Returns the number of timesheet approvals where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @return the number of matching timesheet approvals
	*/
	public int countByTimesheetId(long timesheetId);

	/**
	* Caches the timesheet approval in the entity cache if it is enabled.
	*
	* @param timesheetApproval the timesheet approval
	*/
	public void cacheResult(TimesheetApproval timesheetApproval);

	/**
	* Caches the timesheet approvals in the entity cache if it is enabled.
	*
	* @param timesheetApprovals the timesheet approvals
	*/
	public void cacheResult(
		java.util.List<TimesheetApproval> timesheetApprovals);

	/**
	* Creates a new timesheet approval with the primary key. Does not add the timesheet approval to the database.
	*
	* @param timesheetApprovalId the primary key for the new timesheet approval
	* @return the new timesheet approval
	*/
	public TimesheetApproval create(long timesheetApprovalId);

	/**
	* Removes the timesheet approval with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param timesheetApprovalId the primary key of the timesheet approval
	* @return the timesheet approval that was removed
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public TimesheetApproval remove(long timesheetApprovalId)
		throws NoSuchTimesheetApprovalException;

	public TimesheetApproval updateImpl(TimesheetApproval timesheetApproval);

	/**
	* Returns the timesheet approval with the primary key or throws a {@link NoSuchTimesheetApprovalException} if it could not be found.
	*
	* @param timesheetApprovalId the primary key of the timesheet approval
	* @return the timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public TimesheetApproval findByPrimaryKey(long timesheetApprovalId)
		throws NoSuchTimesheetApprovalException;

	/**
	* Returns the timesheet approval with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param timesheetApprovalId the primary key of the timesheet approval
	* @return the timesheet approval, or <code>null</code> if a timesheet approval with the primary key could not be found
	*/
	public TimesheetApproval fetchByPrimaryKey(long timesheetApprovalId);

	@Override
	public java.util.Map<java.io.Serializable, TimesheetApproval> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the timesheet approvals.
	*
	* @return the timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findAll();

	/**
	* Returns a range of all the timesheet approvals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @return the range of timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findAll(int start, int end);

	/**
	* Returns an ordered range of all the timesheet approvals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator);

	/**
	* Returns an ordered range of all the timesheet approvals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetApprovalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of timesheet approvals
	* @param end the upper bound of the range of timesheet approvals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of timesheet approvals
	*/
	public java.util.List<TimesheetApproval> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the timesheet approvals from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of timesheet approvals.
	*
	* @return the number of timesheet approvals
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}