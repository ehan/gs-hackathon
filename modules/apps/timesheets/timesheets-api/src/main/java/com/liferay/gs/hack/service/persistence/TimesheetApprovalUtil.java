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

import com.liferay.gs.hack.model.TimesheetApproval;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the timesheet approval service. This utility wraps {@link com.liferay.gs.hack.service.persistence.impl.TimesheetApprovalPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TimesheetApprovalPersistence
 * @see com.liferay.gs.hack.service.persistence.impl.TimesheetApprovalPersistenceImpl
 * @generated
 */
@ProviderType
public class TimesheetApprovalUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TimesheetApproval timesheetApproval) {
		getPersistence().clearCache(timesheetApproval);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TimesheetApproval> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TimesheetApproval> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TimesheetApproval> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TimesheetApproval update(TimesheetApproval timesheetApproval) {
		return getPersistence().update(timesheetApproval);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TimesheetApproval update(
		TimesheetApproval timesheetApproval, ServiceContext serviceContext) {
		return getPersistence().update(timesheetApproval, serviceContext);
	}

	/**
	* Returns all the timesheet approvals where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching timesheet approvals
	*/
	public static List<TimesheetApproval> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<TimesheetApproval> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<TimesheetApproval> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<TimesheetApproval> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByUuid_First(java.lang.String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByUuid_Last(java.lang.String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the timesheet approvals before and after the current timesheet approval in the ordered set where uuid = &#63;.
	*
	* @param timesheetApprovalId the primary key of the current timesheet approval
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public static TimesheetApproval[] findByUuid_PrevAndNext(
		long timesheetApprovalId, java.lang.String uuid,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByUuid_PrevAndNext(timesheetApprovalId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the timesheet approvals where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of timesheet approvals where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching timesheet approvals
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the timesheet approval where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTimesheetApprovalException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the timesheet approval where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the timesheet approval where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the timesheet approval where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the timesheet approval that was removed
	*/
	public static TimesheetApproval removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of timesheet approvals where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching timesheet approvals
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching timesheet approvals
	*/
	public static List<TimesheetApproval> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<TimesheetApproval> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<TimesheetApproval> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<TimesheetApproval> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last timesheet approval in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static TimesheetApproval[] findByUuid_C_PrevAndNext(
		long timesheetApprovalId, java.lang.String uuid, long companyId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(timesheetApprovalId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the timesheet approvals where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of timesheet approvals where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching timesheet approvals
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the timesheet approvals where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @return the matching timesheet approvals
	*/
	public static List<TimesheetApproval> findByTimesheetId(long timesheetId) {
		return getPersistence().findByTimesheetId(timesheetId);
	}

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
	public static List<TimesheetApproval> findByTimesheetId(long timesheetId,
		int start, int end) {
		return getPersistence().findByTimesheetId(timesheetId, start, end);
	}

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
	public static List<TimesheetApproval> findByTimesheetId(long timesheetId,
		int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .findByTimesheetId(timesheetId, start, end, orderByComparator);
	}

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
	public static List<TimesheetApproval> findByTimesheetId(long timesheetId,
		int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTimesheetId(timesheetId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByTimesheetId_First(long timesheetId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByTimesheetId_First(timesheetId, orderByComparator);
	}

	/**
	* Returns the first timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByTimesheetId_First(long timesheetId,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .fetchByTimesheetId_First(timesheetId, orderByComparator);
	}

	/**
	* Returns the last timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval
	* @throws NoSuchTimesheetApprovalException if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval findByTimesheetId_Last(long timesheetId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByTimesheetId_Last(timesheetId, orderByComparator);
	}

	/**
	* Returns the last timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching timesheet approval, or <code>null</code> if a matching timesheet approval could not be found
	*/
	public static TimesheetApproval fetchByTimesheetId_Last(long timesheetId,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence()
				   .fetchByTimesheetId_Last(timesheetId, orderByComparator);
	}

	/**
	* Returns the timesheet approvals before and after the current timesheet approval in the ordered set where timesheetId = &#63;.
	*
	* @param timesheetApprovalId the primary key of the current timesheet approval
	* @param timesheetId the timesheet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public static TimesheetApproval[] findByTimesheetId_PrevAndNext(
		long timesheetApprovalId, long timesheetId,
		OrderByComparator<TimesheetApproval> orderByComparator)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence()
				   .findByTimesheetId_PrevAndNext(timesheetApprovalId,
			timesheetId, orderByComparator);
	}

	/**
	* Removes all the timesheet approvals where timesheetId = &#63; from the database.
	*
	* @param timesheetId the timesheet ID
	*/
	public static void removeByTimesheetId(long timesheetId) {
		getPersistence().removeByTimesheetId(timesheetId);
	}

	/**
	* Returns the number of timesheet approvals where timesheetId = &#63;.
	*
	* @param timesheetId the timesheet ID
	* @return the number of matching timesheet approvals
	*/
	public static int countByTimesheetId(long timesheetId) {
		return getPersistence().countByTimesheetId(timesheetId);
	}

	/**
	* Caches the timesheet approval in the entity cache if it is enabled.
	*
	* @param timesheetApproval the timesheet approval
	*/
	public static void cacheResult(TimesheetApproval timesheetApproval) {
		getPersistence().cacheResult(timesheetApproval);
	}

	/**
	* Caches the timesheet approvals in the entity cache if it is enabled.
	*
	* @param timesheetApprovals the timesheet approvals
	*/
	public static void cacheResult(List<TimesheetApproval> timesheetApprovals) {
		getPersistence().cacheResult(timesheetApprovals);
	}

	/**
	* Creates a new timesheet approval with the primary key. Does not add the timesheet approval to the database.
	*
	* @param timesheetApprovalId the primary key for the new timesheet approval
	* @return the new timesheet approval
	*/
	public static TimesheetApproval create(long timesheetApprovalId) {
		return getPersistence().create(timesheetApprovalId);
	}

	/**
	* Removes the timesheet approval with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param timesheetApprovalId the primary key of the timesheet approval
	* @return the timesheet approval that was removed
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public static TimesheetApproval remove(long timesheetApprovalId)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence().remove(timesheetApprovalId);
	}

	public static TimesheetApproval updateImpl(
		TimesheetApproval timesheetApproval) {
		return getPersistence().updateImpl(timesheetApproval);
	}

	/**
	* Returns the timesheet approval with the primary key or throws a {@link NoSuchTimesheetApprovalException} if it could not be found.
	*
	* @param timesheetApprovalId the primary key of the timesheet approval
	* @return the timesheet approval
	* @throws NoSuchTimesheetApprovalException if a timesheet approval with the primary key could not be found
	*/
	public static TimesheetApproval findByPrimaryKey(long timesheetApprovalId)
		throws com.liferay.gs.hack.exception.NoSuchTimesheetApprovalException {
		return getPersistence().findByPrimaryKey(timesheetApprovalId);
	}

	/**
	* Returns the timesheet approval with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param timesheetApprovalId the primary key of the timesheet approval
	* @return the timesheet approval, or <code>null</code> if a timesheet approval with the primary key could not be found
	*/
	public static TimesheetApproval fetchByPrimaryKey(long timesheetApprovalId) {
		return getPersistence().fetchByPrimaryKey(timesheetApprovalId);
	}

	public static java.util.Map<java.io.Serializable, TimesheetApproval> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the timesheet approvals.
	*
	* @return the timesheet approvals
	*/
	public static List<TimesheetApproval> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TimesheetApproval> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TimesheetApproval> findAll(int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TimesheetApproval> findAll(int start, int end,
		OrderByComparator<TimesheetApproval> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the timesheet approvals from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of timesheet approvals.
	*
	* @return the number of timesheet approvals
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TimesheetApprovalPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TimesheetApprovalPersistence, TimesheetApprovalPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TimesheetApprovalPersistence.class);
}