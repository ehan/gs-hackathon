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

package com.liferay.gs.hack.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TimesheetTaskDuration. This utility wraps
 * {@link com.liferay.gs.hack.service.impl.TimesheetTaskDurationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TimesheetTaskDurationLocalService
 * @see com.liferay.gs.hack.service.base.TimesheetTaskDurationLocalServiceBaseImpl
 * @see com.liferay.gs.hack.service.impl.TimesheetTaskDurationLocalServiceImpl
 * @generated
 */
@ProviderType
public class TimesheetTaskDurationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.gs.hack.service.impl.TimesheetTaskDurationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the timesheet task duration to the database. Also notifies the appropriate model listeners.
	*
	* @param timesheetTaskDuration the timesheet task duration
	* @return the timesheet task duration that was added
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration addTimesheetTaskDuration(
		com.liferay.gs.hack.model.TimesheetTaskDuration timesheetTaskDuration) {
		return getService().addTimesheetTaskDuration(timesheetTaskDuration);
	}

	/**
	* Creates a new timesheet task duration with the primary key. Does not add the timesheet task duration to the database.
	*
	* @param timesheetTaskDurationPK the primary key for the new timesheet task duration
	* @return the new timesheet task duration
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration createTimesheetTaskDuration(
		com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPK timesheetTaskDurationPK) {
		return getService().createTimesheetTaskDuration(timesheetTaskDurationPK);
	}

	/**
	* Deletes the timesheet task duration from the database. Also notifies the appropriate model listeners.
	*
	* @param timesheetTaskDuration the timesheet task duration
	* @return the timesheet task duration that was removed
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration deleteTimesheetTaskDuration(
		com.liferay.gs.hack.model.TimesheetTaskDuration timesheetTaskDuration) {
		return getService().deleteTimesheetTaskDuration(timesheetTaskDuration);
	}

	/**
	* Deletes the timesheet task duration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param timesheetTaskDurationPK the primary key of the timesheet task duration
	* @return the timesheet task duration that was removed
	* @throws PortalException if a timesheet task duration with the primary key could not be found
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration deleteTimesheetTaskDuration(
		com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPK timesheetTaskDurationPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTimesheetTaskDuration(timesheetTaskDurationPK);
	}

	public static com.liferay.gs.hack.model.TimesheetTaskDuration fetchTimesheetTaskDuration(
		com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPK timesheetTaskDurationPK) {
		return getService().fetchTimesheetTaskDuration(timesheetTaskDurationPK);
	}

	/**
	* Returns the timesheet task duration matching the UUID and group.
	*
	* @param uuid the timesheet task duration's UUID
	* @param groupId the primary key of the group
	* @return the matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration fetchTimesheetTaskDurationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService()
				   .fetchTimesheetTaskDurationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the timesheet task duration with the primary key.
	*
	* @param timesheetTaskDurationPK the primary key of the timesheet task duration
	* @return the timesheet task duration
	* @throws PortalException if a timesheet task duration with the primary key could not be found
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration getTimesheetTaskDuration(
		com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPK timesheetTaskDurationPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTimesheetTaskDuration(timesheetTaskDurationPK);
	}

	/**
	* Returns the timesheet task duration matching the UUID and group.
	*
	* @param uuid the timesheet task duration's UUID
	* @param groupId the primary key of the group
	* @return the matching timesheet task duration
	* @throws PortalException if a matching timesheet task duration could not be found
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration getTimesheetTaskDurationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getTimesheetTaskDurationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the timesheet task duration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param timesheetTaskDuration the timesheet task duration
	* @return the timesheet task duration that was updated
	*/
	public static com.liferay.gs.hack.model.TimesheetTaskDuration updateTimesheetTaskDuration(
		com.liferay.gs.hack.model.TimesheetTaskDuration timesheetTaskDuration) {
		return getService().updateTimesheetTaskDuration(timesheetTaskDuration);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of timesheet task durations.
	*
	* @return the number of timesheet task durations
	*/
	public static int getTimesheetTaskDurationsCount() {
		return getService().getTimesheetTaskDurationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gs.hack.model.impl.TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gs.hack.model.impl.TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.gs.hack.model.TimesheetTaskDuration> getTimesheetTaskDurationByTimesheetId(
		long timesheetId) {
		return getService().getTimesheetTaskDurationByTimesheetId(timesheetId);
	}

	/**
	* Returns a range of all the timesheet task durations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gs.hack.model.impl.TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of timesheet task durations
	* @param end the upper bound of the range of timesheet task durations (not inclusive)
	* @return the range of timesheet task durations
	*/
	public static java.util.List<com.liferay.gs.hack.model.TimesheetTaskDuration> getTimesheetTaskDurations(
		int start, int end) {
		return getService().getTimesheetTaskDurations(start, end);
	}

	/**
	* Returns all the timesheet task durations matching the UUID and company.
	*
	* @param uuid the UUID of the timesheet task durations
	* @param companyId the primary key of the company
	* @return the matching timesheet task durations, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.gs.hack.model.TimesheetTaskDuration> getTimesheetTaskDurationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getTimesheetTaskDurationsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of timesheet task durations matching the UUID and company.
	*
	* @param uuid the UUID of the timesheet task durations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of timesheet task durations
	* @param end the upper bound of the range of timesheet task durations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching timesheet task durations, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.gs.hack.model.TimesheetTaskDuration> getTimesheetTaskDurationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.gs.hack.model.TimesheetTaskDuration> orderByComparator) {
		return getService()
				   .getTimesheetTaskDurationsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static TimesheetTaskDurationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TimesheetTaskDurationLocalService, TimesheetTaskDurationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TimesheetTaskDurationLocalService.class);
}