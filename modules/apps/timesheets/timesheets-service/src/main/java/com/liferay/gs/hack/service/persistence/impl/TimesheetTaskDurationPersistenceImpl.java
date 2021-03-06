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

package com.liferay.gs.hack.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gs.hack.exception.NoSuchTimesheetTaskDurationException;
import com.liferay.gs.hack.model.TimesheetTaskDuration;
import com.liferay.gs.hack.model.impl.TimesheetTaskDurationImpl;
import com.liferay.gs.hack.model.impl.TimesheetTaskDurationModelImpl;
import com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPK;
import com.liferay.gs.hack.service.persistence.TimesheetTaskDurationPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the timesheet task duration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TimesheetTaskDurationPersistence
 * @see com.liferay.gs.hack.service.persistence.TimesheetTaskDurationUtil
 * @generated
 */
@ProviderType
public class TimesheetTaskDurationPersistenceImpl extends BasePersistenceImpl<TimesheetTaskDuration>
	implements TimesheetTaskDurationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TimesheetTaskDurationUtil} to access the timesheet task duration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TimesheetTaskDurationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			TimesheetTaskDurationModelImpl.UUID_COLUMN_BITMASK |
			TimesheetTaskDurationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the timesheet task durations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet task durations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @return the range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid(String uuid, int start,
		int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid(String uuid, int start,
		int end, OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid(String uuid, int start,
		int end, OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<TimesheetTaskDuration> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetTaskDuration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimesheetTaskDuration timesheetTaskDuration : list) {
					if (!Objects.equals(uuid, timesheetTaskDuration.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByUuid_First(String uuid,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByUuid_First(uuid,
				orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByUuid_First(String uuid,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		List<TimesheetTaskDuration> list = findByUuid(uuid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByUuid_Last(String uuid,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByUuid_Last(uuid,
				orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByUuid_Last(String uuid,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<TimesheetTaskDuration> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timesheet task durations before and after the current timesheet task duration in the ordered set where uuid = &#63;.
	 *
	 * @param timesheetTaskDurationPK the primary key of the current timesheet task duration
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration[] findByUuid_PrevAndNext(
		TimesheetTaskDurationPK timesheetTaskDurationPK, String uuid,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = findByPrimaryKey(timesheetTaskDurationPK);

		Session session = null;

		try {
			session = openSession();

			TimesheetTaskDuration[] array = new TimesheetTaskDurationImpl[3];

			array[0] = getByUuid_PrevAndNext(session, timesheetTaskDuration,
					uuid, orderByComparator, true);

			array[1] = timesheetTaskDuration;

			array[2] = getByUuid_PrevAndNext(session, timesheetTaskDuration,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimesheetTaskDuration getByUuid_PrevAndNext(Session session,
		TimesheetTaskDuration timesheetTaskDuration, String uuid,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(timesheetTaskDuration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TimesheetTaskDuration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timesheet task durations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (TimesheetTaskDuration timesheetTaskDuration : findByUuid(uuid,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(timesheetTaskDuration);
		}
	}

	/**
	 * Returns the number of timesheet task durations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching timesheet task durations
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TIMESHEETTASKDURATION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "timesheetTaskDuration.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "timesheetTaskDuration.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(timesheetTaskDuration.uuid IS NULL OR timesheetTaskDuration.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			TimesheetTaskDurationModelImpl.UUID_COLUMN_BITMASK |
			TimesheetTaskDurationModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the timesheet task duration where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchTimesheetTaskDurationException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByUUID_G(String uuid, long groupId)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByUUID_G(uuid,
				groupId);

		if (timesheetTaskDuration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchTimesheetTaskDurationException(msg.toString());
		}

		return timesheetTaskDuration;
	}

	/**
	 * Returns the timesheet task duration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the timesheet task duration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof TimesheetTaskDuration) {
			TimesheetTaskDuration timesheetTaskDuration = (TimesheetTaskDuration)result;

			if (!Objects.equals(uuid, timesheetTaskDuration.getUuid()) ||
					(groupId != timesheetTaskDuration.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<TimesheetTaskDuration> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					TimesheetTaskDuration timesheetTaskDuration = list.get(0);

					result = timesheetTaskDuration;

					cacheResult(timesheetTaskDuration);

					if ((timesheetTaskDuration.getUuid() == null) ||
							!timesheetTaskDuration.getUuid().equals(uuid) ||
							(timesheetTaskDuration.getGroupId() != groupId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, timesheetTaskDuration);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (TimesheetTaskDuration)result;
		}
	}

	/**
	 * Removes the timesheet task duration where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the timesheet task duration that was removed
	 */
	@Override
	public TimesheetTaskDuration removeByUUID_G(String uuid, long groupId)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = findByUUID_G(uuid, groupId);

		return remove(timesheetTaskDuration);
	}

	/**
	 * Returns the number of timesheet task durations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching timesheet task durations
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TIMESHEETTASKDURATION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "timesheetTaskDuration.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "timesheetTaskDuration.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(timesheetTaskDuration.uuid IS NULL OR timesheetTaskDuration.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "timesheetTaskDuration.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			TimesheetTaskDurationModelImpl.UUID_COLUMN_BITMASK |
			TimesheetTaskDurationModelImpl.COMPANYID_COLUMN_BITMASK |
			TimesheetTaskDurationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the timesheet task durations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet task durations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @return the range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<TimesheetTaskDuration> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetTaskDuration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimesheetTaskDuration timesheetTaskDuration : list) {
					if (!Objects.equals(uuid, timesheetTaskDuration.getUuid()) ||
							(companyId != timesheetTaskDuration.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByUuid_C_First(uuid,
				companyId, orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByUuid_C_First(String uuid,
		long companyId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		List<TimesheetTaskDuration> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByUuid_C_Last(uuid,
				companyId, orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByUuid_C_Last(String uuid,
		long companyId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<TimesheetTaskDuration> list = findByUuid_C(uuid, companyId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timesheet task durations before and after the current timesheet task duration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param timesheetTaskDurationPK the primary key of the current timesheet task duration
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration[] findByUuid_C_PrevAndNext(
		TimesheetTaskDurationPK timesheetTaskDurationPK, String uuid,
		long companyId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = findByPrimaryKey(timesheetTaskDurationPK);

		Session session = null;

		try {
			session = openSession();

			TimesheetTaskDuration[] array = new TimesheetTaskDurationImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, timesheetTaskDuration,
					uuid, companyId, orderByComparator, true);

			array[1] = timesheetTaskDuration;

			array[2] = getByUuid_C_PrevAndNext(session, timesheetTaskDuration,
					uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimesheetTaskDuration getByUuid_C_PrevAndNext(Session session,
		TimesheetTaskDuration timesheetTaskDuration, String uuid,
		long companyId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(timesheetTaskDuration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TimesheetTaskDuration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timesheet task durations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (TimesheetTaskDuration timesheetTaskDuration : findByUuid_C(uuid,
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(timesheetTaskDuration);
		}
	}

	/**
	 * Returns the number of timesheet task durations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching timesheet task durations
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TIMESHEETTASKDURATION_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "timesheetTaskDuration.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "timesheetTaskDuration.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(timesheetTaskDuration.uuid IS NULL OR timesheetTaskDuration.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "timesheetTaskDuration.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TIMESHEETID =
		new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTimesheetId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETID =
		new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTimesheetId",
			new String[] { Long.class.getName() },
			TimesheetTaskDurationModelImpl.TIMESHEETID_COLUMN_BITMASK |
			TimesheetTaskDurationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TIMESHEETID = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTimesheetId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the timesheet task durations where timesheetId = &#63;.
	 *
	 * @param timesheetId the timesheet ID
	 * @return the matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetId(long timesheetId) {
		return findByTimesheetId(timesheetId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet task durations where timesheetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param timesheetId the timesheet ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @return the range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetId(long timesheetId,
		int start, int end) {
		return findByTimesheetId(timesheetId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where timesheetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param timesheetId the timesheet ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetId(long timesheetId,
		int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		return findByTimesheetId(timesheetId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where timesheetId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param timesheetId the timesheet ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetId(long timesheetId,
		int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETID;
			finderArgs = new Object[] { timesheetId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TIMESHEETID;
			finderArgs = new Object[] { timesheetId, start, end, orderByComparator };
		}

		List<TimesheetTaskDuration> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetTaskDuration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimesheetTaskDuration timesheetTaskDuration : list) {
					if ((timesheetId != timesheetTaskDuration.getTimesheetId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

			query.append(_FINDER_COLUMN_TIMESHEETID_TIMESHEETID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(timesheetId);

				if (!pagination) {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where timesheetId = &#63;.
	 *
	 * @param timesheetId the timesheet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByTimesheetId_First(long timesheetId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByTimesheetId_First(timesheetId,
				orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("timesheetId=");
		msg.append(timesheetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where timesheetId = &#63;.
	 *
	 * @param timesheetId the timesheet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByTimesheetId_First(long timesheetId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		List<TimesheetTaskDuration> list = findByTimesheetId(timesheetId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where timesheetId = &#63;.
	 *
	 * @param timesheetId the timesheet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByTimesheetId_Last(long timesheetId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByTimesheetId_Last(timesheetId,
				orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("timesheetId=");
		msg.append(timesheetId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where timesheetId = &#63;.
	 *
	 * @param timesheetId the timesheet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByTimesheetId_Last(long timesheetId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		int count = countByTimesheetId(timesheetId);

		if (count == 0) {
			return null;
		}

		List<TimesheetTaskDuration> list = findByTimesheetId(timesheetId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timesheet task durations before and after the current timesheet task duration in the ordered set where timesheetId = &#63;.
	 *
	 * @param timesheetTaskDurationPK the primary key of the current timesheet task duration
	 * @param timesheetId the timesheet ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration[] findByTimesheetId_PrevAndNext(
		TimesheetTaskDurationPK timesheetTaskDurationPK, long timesheetId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = findByPrimaryKey(timesheetTaskDurationPK);

		Session session = null;

		try {
			session = openSession();

			TimesheetTaskDuration[] array = new TimesheetTaskDurationImpl[3];

			array[0] = getByTimesheetId_PrevAndNext(session,
					timesheetTaskDuration, timesheetId, orderByComparator, true);

			array[1] = timesheetTaskDuration;

			array[2] = getByTimesheetId_PrevAndNext(session,
					timesheetTaskDuration, timesheetId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimesheetTaskDuration getByTimesheetId_PrevAndNext(
		Session session, TimesheetTaskDuration timesheetTaskDuration,
		long timesheetId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

		query.append(_FINDER_COLUMN_TIMESHEETID_TIMESHEETID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(timesheetId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(timesheetTaskDuration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TimesheetTaskDuration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timesheet task durations where timesheetId = &#63; from the database.
	 *
	 * @param timesheetId the timesheet ID
	 */
	@Override
	public void removeByTimesheetId(long timesheetId) {
		for (TimesheetTaskDuration timesheetTaskDuration : findByTimesheetId(
				timesheetId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(timesheetTaskDuration);
		}
	}

	/**
	 * Returns the number of timesheet task durations where timesheetId = &#63;.
	 *
	 * @param timesheetId the timesheet ID
	 * @return the number of matching timesheet task durations
	 */
	@Override
	public int countByTimesheetId(long timesheetId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TIMESHEETID;

		Object[] finderArgs = new Object[] { timesheetId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TIMESHEETTASKDURATION_WHERE);

			query.append(_FINDER_COLUMN_TIMESHEETID_TIMESHEETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(timesheetId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TIMESHEETID_TIMESHEETID_2 = "timesheetTaskDuration.id.timesheetId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TIMESHEETTASKID =
		new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTimesheetTaskId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETTASKID =
		new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTimesheetTaskId",
			new String[] { Long.class.getName() },
			TimesheetTaskDurationModelImpl.TIMESHEETTASKID_COLUMN_BITMASK |
			TimesheetTaskDurationModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TIMESHEETTASKID = new FinderPath(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByTimesheetTaskId", new String[] { Long.class.getName() });

	/**
	 * Returns all the timesheet task durations where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @return the matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetTaskId(
		long timesheetTaskId) {
		return findByTimesheetTaskId(timesheetTaskId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet task durations where timesheetTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @return the range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetTaskId(
		long timesheetTaskId, int start, int end) {
		return findByTimesheetTaskId(timesheetTaskId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where timesheetTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetTaskId(
		long timesheetTaskId, int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		return findByTimesheetTaskId(timesheetTaskId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations where timesheetTaskId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findByTimesheetTaskId(
		long timesheetTaskId, int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETTASKID;
			finderArgs = new Object[] { timesheetTaskId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TIMESHEETTASKID;
			finderArgs = new Object[] {
					timesheetTaskId,
					
					start, end, orderByComparator
				};
		}

		List<TimesheetTaskDuration> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetTaskDuration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TimesheetTaskDuration timesheetTaskDuration : list) {
					if ((timesheetTaskId != timesheetTaskDuration.getTimesheetTaskId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

			query.append(_FINDER_COLUMN_TIMESHEETTASKID_TIMESHEETTASKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(timesheetTaskId);

				if (!pagination) {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByTimesheetTaskId_First(
		long timesheetTaskId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByTimesheetTaskId_First(timesheetTaskId,
				orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("timesheetTaskId=");
		msg.append(timesheetTaskId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the first timesheet task duration in the ordered set where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByTimesheetTaskId_First(
		long timesheetTaskId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		List<TimesheetTaskDuration> list = findByTimesheetTaskId(timesheetTaskId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration findByTimesheetTaskId_Last(
		long timesheetTaskId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByTimesheetTaskId_Last(timesheetTaskId,
				orderByComparator);

		if (timesheetTaskDuration != null) {
			return timesheetTaskDuration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("timesheetTaskId=");
		msg.append(timesheetTaskId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTimesheetTaskDurationException(msg.toString());
	}

	/**
	 * Returns the last timesheet task duration in the ordered set where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching timesheet task duration, or <code>null</code> if a matching timesheet task duration could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByTimesheetTaskId_Last(
		long timesheetTaskId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		int count = countByTimesheetTaskId(timesheetTaskId);

		if (count == 0) {
			return null;
		}

		List<TimesheetTaskDuration> list = findByTimesheetTaskId(timesheetTaskId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the timesheet task durations before and after the current timesheet task duration in the ordered set where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskDurationPK the primary key of the current timesheet task duration
	 * @param timesheetTaskId the timesheet task ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration[] findByTimesheetTaskId_PrevAndNext(
		TimesheetTaskDurationPK timesheetTaskDurationPK, long timesheetTaskId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = findByPrimaryKey(timesheetTaskDurationPK);

		Session session = null;

		try {
			session = openSession();

			TimesheetTaskDuration[] array = new TimesheetTaskDurationImpl[3];

			array[0] = getByTimesheetTaskId_PrevAndNext(session,
					timesheetTaskDuration, timesheetTaskId, orderByComparator,
					true);

			array[1] = timesheetTaskDuration;

			array[2] = getByTimesheetTaskId_PrevAndNext(session,
					timesheetTaskDuration, timesheetTaskId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected TimesheetTaskDuration getByTimesheetTaskId_PrevAndNext(
		Session session, TimesheetTaskDuration timesheetTaskDuration,
		long timesheetTaskId,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TIMESHEETTASKDURATION_WHERE);

		query.append(_FINDER_COLUMN_TIMESHEETTASKID_TIMESHEETTASKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(timesheetTaskId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(timesheetTaskDuration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<TimesheetTaskDuration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the timesheet task durations where timesheetTaskId = &#63; from the database.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 */
	@Override
	public void removeByTimesheetTaskId(long timesheetTaskId) {
		for (TimesheetTaskDuration timesheetTaskDuration : findByTimesheetTaskId(
				timesheetTaskId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(timesheetTaskDuration);
		}
	}

	/**
	 * Returns the number of timesheet task durations where timesheetTaskId = &#63;.
	 *
	 * @param timesheetTaskId the timesheet task ID
	 * @return the number of matching timesheet task durations
	 */
	@Override
	public int countByTimesheetTaskId(long timesheetTaskId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TIMESHEETTASKID;

		Object[] finderArgs = new Object[] { timesheetTaskId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TIMESHEETTASKDURATION_WHERE);

			query.append(_FINDER_COLUMN_TIMESHEETTASKID_TIMESHEETTASKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(timesheetTaskId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TIMESHEETTASKID_TIMESHEETTASKID_2 =
		"timesheetTaskDuration.id.timesheetTaskId = ?";

	public TimesheetTaskDurationPersistenceImpl() {
		setModelClass(TimesheetTaskDuration.class);
	}

	/**
	 * Caches the timesheet task duration in the entity cache if it is enabled.
	 *
	 * @param timesheetTaskDuration the timesheet task duration
	 */
	@Override
	public void cacheResult(TimesheetTaskDuration timesheetTaskDuration) {
		entityCache.putResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			timesheetTaskDuration.getPrimaryKey(), timesheetTaskDuration);

		finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				timesheetTaskDuration.getUuid(),
				timesheetTaskDuration.getGroupId()
			}, timesheetTaskDuration);

		timesheetTaskDuration.resetOriginalValues();
	}

	/**
	 * Caches the timesheet task durations in the entity cache if it is enabled.
	 *
	 * @param timesheetTaskDurations the timesheet task durations
	 */
	@Override
	public void cacheResult(List<TimesheetTaskDuration> timesheetTaskDurations) {
		for (TimesheetTaskDuration timesheetTaskDuration : timesheetTaskDurations) {
			if (entityCache.getResult(
						TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
						TimesheetTaskDurationImpl.class,
						timesheetTaskDuration.getPrimaryKey()) == null) {
				cacheResult(timesheetTaskDuration);
			}
			else {
				timesheetTaskDuration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all timesheet task durations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TimesheetTaskDurationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the timesheet task duration.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TimesheetTaskDuration timesheetTaskDuration) {
		entityCache.removeResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			timesheetTaskDuration.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((TimesheetTaskDurationModelImpl)timesheetTaskDuration);
	}

	@Override
	public void clearCache(List<TimesheetTaskDuration> timesheetTaskDurations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TimesheetTaskDuration timesheetTaskDuration : timesheetTaskDurations) {
			entityCache.removeResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
				TimesheetTaskDurationImpl.class,
				timesheetTaskDuration.getPrimaryKey());

			clearUniqueFindersCache((TimesheetTaskDurationModelImpl)timesheetTaskDuration);
		}
	}

	protected void cacheUniqueFindersCache(
		TimesheetTaskDurationModelImpl timesheetTaskDurationModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					timesheetTaskDurationModelImpl.getUuid(),
					timesheetTaskDurationModelImpl.getGroupId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				timesheetTaskDurationModelImpl);
		}
		else {
			if ((timesheetTaskDurationModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetTaskDurationModelImpl.getUuid(),
						timesheetTaskDurationModelImpl.getGroupId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					timesheetTaskDurationModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		TimesheetTaskDurationModelImpl timesheetTaskDurationModelImpl) {
		Object[] args = new Object[] {
				timesheetTaskDurationModelImpl.getUuid(),
				timesheetTaskDurationModelImpl.getGroupId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((timesheetTaskDurationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					timesheetTaskDurationModelImpl.getOriginalUuid(),
					timesheetTaskDurationModelImpl.getOriginalGroupId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new timesheet task duration with the primary key. Does not add the timesheet task duration to the database.
	 *
	 * @param timesheetTaskDurationPK the primary key for the new timesheet task duration
	 * @return the new timesheet task duration
	 */
	@Override
	public TimesheetTaskDuration create(
		TimesheetTaskDurationPK timesheetTaskDurationPK) {
		TimesheetTaskDuration timesheetTaskDuration = new TimesheetTaskDurationImpl();

		timesheetTaskDuration.setNew(true);
		timesheetTaskDuration.setPrimaryKey(timesheetTaskDurationPK);

		String uuid = PortalUUIDUtil.generate();

		timesheetTaskDuration.setUuid(uuid);

		timesheetTaskDuration.setCompanyId(companyProvider.getCompanyId());

		return timesheetTaskDuration;
	}

	/**
	 * Removes the timesheet task duration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param timesheetTaskDurationPK the primary key of the timesheet task duration
	 * @return the timesheet task duration that was removed
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration remove(
		TimesheetTaskDurationPK timesheetTaskDurationPK)
		throws NoSuchTimesheetTaskDurationException {
		return remove((Serializable)timesheetTaskDurationPK);
	}

	/**
	 * Removes the timesheet task duration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the timesheet task duration
	 * @return the timesheet task duration that was removed
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration remove(Serializable primaryKey)
		throws NoSuchTimesheetTaskDurationException {
		Session session = null;

		try {
			session = openSession();

			TimesheetTaskDuration timesheetTaskDuration = (TimesheetTaskDuration)session.get(TimesheetTaskDurationImpl.class,
					primaryKey);

			if (timesheetTaskDuration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTimesheetTaskDurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(timesheetTaskDuration);
		}
		catch (NoSuchTimesheetTaskDurationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TimesheetTaskDuration removeImpl(
		TimesheetTaskDuration timesheetTaskDuration) {
		timesheetTaskDuration = toUnwrappedModel(timesheetTaskDuration);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(timesheetTaskDuration)) {
				timesheetTaskDuration = (TimesheetTaskDuration)session.get(TimesheetTaskDurationImpl.class,
						timesheetTaskDuration.getPrimaryKeyObj());
			}

			if (timesheetTaskDuration != null) {
				session.delete(timesheetTaskDuration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (timesheetTaskDuration != null) {
			clearCache(timesheetTaskDuration);
		}

		return timesheetTaskDuration;
	}

	@Override
	public TimesheetTaskDuration updateImpl(
		TimesheetTaskDuration timesheetTaskDuration) {
		timesheetTaskDuration = toUnwrappedModel(timesheetTaskDuration);

		boolean isNew = timesheetTaskDuration.isNew();

		TimesheetTaskDurationModelImpl timesheetTaskDurationModelImpl = (TimesheetTaskDurationModelImpl)timesheetTaskDuration;

		if (Validator.isNull(timesheetTaskDuration.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			timesheetTaskDuration.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (timesheetTaskDuration.getCreateDate() == null)) {
			if (serviceContext == null) {
				timesheetTaskDuration.setCreateDate(now);
			}
			else {
				timesheetTaskDuration.setCreateDate(serviceContext.getCreateDate(
						now));
			}
		}

		if (!timesheetTaskDurationModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				timesheetTaskDuration.setModifiedDate(now);
			}
			else {
				timesheetTaskDuration.setModifiedDate(serviceContext.getModifiedDate(
						now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (timesheetTaskDuration.isNew()) {
				session.save(timesheetTaskDuration);

				timesheetTaskDuration.setNew(false);
			}
			else {
				timesheetTaskDuration = (TimesheetTaskDuration)session.merge(timesheetTaskDuration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !TimesheetTaskDurationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((timesheetTaskDurationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetTaskDurationModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { timesheetTaskDurationModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((timesheetTaskDurationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetTaskDurationModelImpl.getOriginalUuid(),
						timesheetTaskDurationModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						timesheetTaskDurationModelImpl.getUuid(),
						timesheetTaskDurationModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((timesheetTaskDurationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetTaskDurationModelImpl.getOriginalTimesheetId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TIMESHEETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETID,
					args);

				args = new Object[] {
						timesheetTaskDurationModelImpl.getTimesheetId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TIMESHEETID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETID,
					args);
			}

			if ((timesheetTaskDurationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETTASKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						timesheetTaskDurationModelImpl.getOriginalTimesheetTaskId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TIMESHEETTASKID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETTASKID,
					args);

				args = new Object[] {
						timesheetTaskDurationModelImpl.getTimesheetTaskId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_TIMESHEETTASKID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TIMESHEETTASKID,
					args);
			}
		}

		entityCache.putResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
			TimesheetTaskDurationImpl.class,
			timesheetTaskDuration.getPrimaryKey(), timesheetTaskDuration, false);

		clearUniqueFindersCache(timesheetTaskDurationModelImpl);
		cacheUniqueFindersCache(timesheetTaskDurationModelImpl, isNew);

		timesheetTaskDuration.resetOriginalValues();

		return timesheetTaskDuration;
	}

	protected TimesheetTaskDuration toUnwrappedModel(
		TimesheetTaskDuration timesheetTaskDuration) {
		if (timesheetTaskDuration instanceof TimesheetTaskDurationImpl) {
			return timesheetTaskDuration;
		}

		TimesheetTaskDurationImpl timesheetTaskDurationImpl = new TimesheetTaskDurationImpl();

		timesheetTaskDurationImpl.setNew(timesheetTaskDuration.isNew());
		timesheetTaskDurationImpl.setPrimaryKey(timesheetTaskDuration.getPrimaryKey());

		timesheetTaskDurationImpl.setUuid(timesheetTaskDuration.getUuid());
		timesheetTaskDurationImpl.setTimesheetTaskDurationId(timesheetTaskDuration.getTimesheetTaskDurationId());
		timesheetTaskDurationImpl.setGroupId(timesheetTaskDuration.getGroupId());
		timesheetTaskDurationImpl.setTimesheetId(timesheetTaskDuration.getTimesheetId());
		timesheetTaskDurationImpl.setTimesheetTaskId(timesheetTaskDuration.getTimesheetTaskId());
		timesheetTaskDurationImpl.setCompanyId(timesheetTaskDuration.getCompanyId());
		timesheetTaskDurationImpl.setUserId(timesheetTaskDuration.getUserId());
		timesheetTaskDurationImpl.setUserName(timesheetTaskDuration.getUserName());
		timesheetTaskDurationImpl.setCreateDate(timesheetTaskDuration.getCreateDate());
		timesheetTaskDurationImpl.setModifiedDate(timesheetTaskDuration.getModifiedDate());
		timesheetTaskDurationImpl.setDuration(timesheetTaskDuration.getDuration());
		timesheetTaskDurationImpl.setComment(timesheetTaskDuration.getComment());
		timesheetTaskDurationImpl.setTaskDate(timesheetTaskDuration.getTaskDate());

		return timesheetTaskDurationImpl;
	}

	/**
	 * Returns the timesheet task duration with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the timesheet task duration
	 * @return the timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTimesheetTaskDurationException {
		TimesheetTaskDuration timesheetTaskDuration = fetchByPrimaryKey(primaryKey);

		if (timesheetTaskDuration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTimesheetTaskDurationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return timesheetTaskDuration;
	}

	/**
	 * Returns the timesheet task duration with the primary key or throws a {@link NoSuchTimesheetTaskDurationException} if it could not be found.
	 *
	 * @param timesheetTaskDurationPK the primary key of the timesheet task duration
	 * @return the timesheet task duration
	 * @throws NoSuchTimesheetTaskDurationException if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration findByPrimaryKey(
		TimesheetTaskDurationPK timesheetTaskDurationPK)
		throws NoSuchTimesheetTaskDurationException {
		return findByPrimaryKey((Serializable)timesheetTaskDurationPK);
	}

	/**
	 * Returns the timesheet task duration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the timesheet task duration
	 * @return the timesheet task duration, or <code>null</code> if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
				TimesheetTaskDurationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TimesheetTaskDuration timesheetTaskDuration = (TimesheetTaskDuration)serializable;

		if (timesheetTaskDuration == null) {
			Session session = null;

			try {
				session = openSession();

				timesheetTaskDuration = (TimesheetTaskDuration)session.get(TimesheetTaskDurationImpl.class,
						primaryKey);

				if (timesheetTaskDuration != null) {
					cacheResult(timesheetTaskDuration);
				}
				else {
					entityCache.putResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
						TimesheetTaskDurationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TimesheetTaskDurationModelImpl.ENTITY_CACHE_ENABLED,
					TimesheetTaskDurationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return timesheetTaskDuration;
	}

	/**
	 * Returns the timesheet task duration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param timesheetTaskDurationPK the primary key of the timesheet task duration
	 * @return the timesheet task duration, or <code>null</code> if a timesheet task duration with the primary key could not be found
	 */
	@Override
	public TimesheetTaskDuration fetchByPrimaryKey(
		TimesheetTaskDurationPK timesheetTaskDurationPK) {
		return fetchByPrimaryKey((Serializable)timesheetTaskDurationPK);
	}

	@Override
	public Map<Serializable, TimesheetTaskDuration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TimesheetTaskDuration> map = new HashMap<Serializable, TimesheetTaskDuration>();

		for (Serializable primaryKey : primaryKeys) {
			TimesheetTaskDuration timesheetTaskDuration = fetchByPrimaryKey(primaryKey);

			if (timesheetTaskDuration != null) {
				map.put(primaryKey, timesheetTaskDuration);
			}
		}

		return map;
	}

	/**
	 * Returns all the timesheet task durations.
	 *
	 * @return the timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the timesheet task durations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @return the range of timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findAll(int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the timesheet task durations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TimesheetTaskDurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of timesheet task durations
	 * @param end the upper bound of the range of timesheet task durations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of timesheet task durations
	 */
	@Override
	public List<TimesheetTaskDuration> findAll(int start, int end,
		OrderByComparator<TimesheetTaskDuration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<TimesheetTaskDuration> list = null;

		if (retrieveFromCache) {
			list = (List<TimesheetTaskDuration>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TIMESHEETTASKDURATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TIMESHEETTASKDURATION;

				if (pagination) {
					sql = sql.concat(TimesheetTaskDurationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TimesheetTaskDuration>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the timesheet task durations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TimesheetTaskDuration timesheetTaskDuration : findAll()) {
			remove(timesheetTaskDuration);
		}
	}

	/**
	 * Returns the number of timesheet task durations.
	 *
	 * @return the number of timesheet task durations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TIMESHEETTASKDURATION);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TimesheetTaskDurationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the timesheet task duration persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TimesheetTaskDurationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_TIMESHEETTASKDURATION = "SELECT timesheetTaskDuration FROM TimesheetTaskDuration timesheetTaskDuration";
	private static final String _SQL_SELECT_TIMESHEETTASKDURATION_WHERE = "SELECT timesheetTaskDuration FROM TimesheetTaskDuration timesheetTaskDuration WHERE ";
	private static final String _SQL_COUNT_TIMESHEETTASKDURATION = "SELECT COUNT(timesheetTaskDuration) FROM TimesheetTaskDuration timesheetTaskDuration";
	private static final String _SQL_COUNT_TIMESHEETTASKDURATION_WHERE = "SELECT COUNT(timesheetTaskDuration) FROM TimesheetTaskDuration timesheetTaskDuration WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "timesheetTaskDuration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TimesheetTaskDuration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TimesheetTaskDuration exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(TimesheetTaskDurationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "comment"
			});
}