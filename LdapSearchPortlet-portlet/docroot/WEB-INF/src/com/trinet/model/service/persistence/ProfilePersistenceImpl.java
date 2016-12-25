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

package com.trinet.model.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.trinet.model.NoSuchProfileException;
import com.trinet.model.model.Profile;
import com.trinet.model.model.impl.ProfileImpl;
import com.trinet.model.model.impl.ProfileModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author ronak.parekh
 * @see ProfilePersistence
 * @see ProfileUtil
 * @generated
 */
public class ProfilePersistenceImpl extends BasePersistenceImpl<Profile>
	implements ProfilePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProfileUtil} to access the profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProfileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileModelImpl.FINDER_CACHE_ENABLED, ProfileImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileModelImpl.FINDER_CACHE_ENABLED, ProfileImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_USERFINDER = new FinderPath(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileModelImpl.FINDER_CACHE_ENABLED, ProfileImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByuserFinder",
			new String[] { String.class.getName() },
			ProfileModelImpl.EMAIL_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERFINDER = new FinderPath(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserFinder",
			new String[] { String.class.getName() });

	/**
	 * Returns the profile where email = &#63; or throws a {@link com.trinet.model.NoSuchProfileException} if it could not be found.
	 *
	 * @param email the email
	 * @return the matching profile
	 * @throws com.trinet.model.NoSuchProfileException if a matching profile could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile findByuserFinder(String email)
		throws NoSuchProfileException, SystemException {
		Profile profile = fetchByuserFinder(email);

		if (profile == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("email=");
			msg.append(email);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchProfileException(msg.toString());
		}

		return profile;
	}

	/**
	 * Returns the profile where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param email the email
	 * @return the matching profile, or <code>null</code> if a matching profile could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile fetchByuserFinder(String email) throws SystemException {
		return fetchByuserFinder(email, true);
	}

	/**
	 * Returns the profile where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param email the email
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching profile, or <code>null</code> if a matching profile could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile fetchByuserFinder(String email, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { email };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERFINDER,
					finderArgs, this);
		}

		if (result instanceof Profile) {
			Profile profile = (Profile)result;

			if (!Validator.equals(email, profile.getEmail())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PROFILE_WHERE);

			boolean bindEmail = false;

			if (email == null) {
				query.append(_FINDER_COLUMN_USERFINDER_EMAIL_1);
			}
			else if (email.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERFINDER_EMAIL_3);
			}
			else {
				bindEmail = true;

				query.append(_FINDER_COLUMN_USERFINDER_EMAIL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmail) {
					qPos.add(email);
				}

				List<Profile> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERFINDER,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"ProfilePersistenceImpl.fetchByuserFinder(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					Profile profile = list.get(0);

					result = profile;

					cacheResult(profile);

					if ((profile.getEmail() == null) ||
							!profile.getEmail().equals(email)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERFINDER,
							finderArgs, profile);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERFINDER,
					finderArgs);

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
			return (Profile)result;
		}
	}

	/**
	 * Removes the profile where email = &#63; from the database.
	 *
	 * @param email the email
	 * @return the profile that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile removeByuserFinder(String email)
		throws NoSuchProfileException, SystemException {
		Profile profile = findByuserFinder(email);

		return remove(profile);
	}

	/**
	 * Returns the number of profiles where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching profiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByuserFinder(String email) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERFINDER;

		Object[] finderArgs = new Object[] { email };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PROFILE_WHERE);

			boolean bindEmail = false;

			if (email == null) {
				query.append(_FINDER_COLUMN_USERFINDER_EMAIL_1);
			}
			else if (email.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_USERFINDER_EMAIL_3);
			}
			else {
				bindEmail = true;

				query.append(_FINDER_COLUMN_USERFINDER_EMAIL_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEmail) {
					qPos.add(email);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERFINDER_EMAIL_1 = "profile.email IS NULL";
	private static final String _FINDER_COLUMN_USERFINDER_EMAIL_2 = "profile.email = ?";
	private static final String _FINDER_COLUMN_USERFINDER_EMAIL_3 = "(profile.email IS NULL OR profile.email = '')";

	public ProfilePersistenceImpl() {
		setModelClass(Profile.class);
	}

	/**
	 * Caches the profile in the entity cache if it is enabled.
	 *
	 * @param profile the profile
	 */
	@Override
	public void cacheResult(Profile profile) {
		EntityCacheUtil.putResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileImpl.class, profile.getPrimaryKey(), profile);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERFINDER,
			new Object[] { profile.getEmail() }, profile);

		profile.resetOriginalValues();
	}

	/**
	 * Caches the profiles in the entity cache if it is enabled.
	 *
	 * @param profiles the profiles
	 */
	@Override
	public void cacheResult(List<Profile> profiles) {
		for (Profile profile : profiles) {
			if (EntityCacheUtil.getResult(
						ProfileModelImpl.ENTITY_CACHE_ENABLED,
						ProfileImpl.class, profile.getPrimaryKey()) == null) {
				cacheResult(profile);
			}
			else {
				profile.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all profiles.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ProfileImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ProfileImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the profile.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Profile profile) {
		EntityCacheUtil.removeResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileImpl.class, profile.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(profile);
	}

	@Override
	public void clearCache(List<Profile> profiles) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Profile profile : profiles) {
			EntityCacheUtil.removeResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
				ProfileImpl.class, profile.getPrimaryKey());

			clearUniqueFindersCache(profile);
		}
	}

	protected void cacheUniqueFindersCache(Profile profile) {
		if (profile.isNew()) {
			Object[] args = new Object[] { profile.getEmail() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERFINDER, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERFINDER, args,
				profile);
		}
		else {
			ProfileModelImpl profileModelImpl = (ProfileModelImpl)profile;

			if ((profileModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_USERFINDER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { profile.getEmail() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERFINDER,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERFINDER,
					args, profile);
			}
		}
	}

	protected void clearUniqueFindersCache(Profile profile) {
		ProfileModelImpl profileModelImpl = (ProfileModelImpl)profile;

		Object[] args = new Object[] { profile.getEmail() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERFINDER, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERFINDER, args);

		if ((profileModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_USERFINDER.getColumnBitmask()) != 0) {
			args = new Object[] { profileModelImpl.getOriginalEmail() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERFINDER, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERFINDER, args);
		}
	}

	/**
	 * Creates a new profile with the primary key. Does not add the profile to the database.
	 *
	 * @param email the primary key for the new profile
	 * @return the new profile
	 */
	@Override
	public Profile create(String email) {
		Profile profile = new ProfileImpl();

		profile.setNew(true);
		profile.setPrimaryKey(email);

		return profile;
	}

	/**
	 * Removes the profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param email the primary key of the profile
	 * @return the profile that was removed
	 * @throws com.trinet.model.NoSuchProfileException if a profile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile remove(String email)
		throws NoSuchProfileException, SystemException {
		return remove((Serializable)email);
	}

	/**
	 * Removes the profile with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the profile
	 * @return the profile that was removed
	 * @throws com.trinet.model.NoSuchProfileException if a profile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile remove(Serializable primaryKey)
		throws NoSuchProfileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Profile profile = (Profile)session.get(ProfileImpl.class, primaryKey);

			if (profile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProfileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(profile);
		}
		catch (NoSuchProfileException nsee) {
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
	protected Profile removeImpl(Profile profile) throws SystemException {
		profile = toUnwrappedModel(profile);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(profile)) {
				profile = (Profile)session.get(ProfileImpl.class,
						profile.getPrimaryKeyObj());
			}

			if (profile != null) {
				session.delete(profile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (profile != null) {
			clearCache(profile);
		}

		return profile;
	}

	@Override
	public Profile updateImpl(com.trinet.model.model.Profile profile)
		throws SystemException {
		profile = toUnwrappedModel(profile);

		boolean isNew = profile.isNew();

		Session session = null;

		try {
			session = openSession();

			if (profile.isNew()) {
				session.save(profile);

				profile.setNew(false);
			}
			else {
				session.merge(profile);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ProfileModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
			ProfileImpl.class, profile.getPrimaryKey(), profile);

		clearUniqueFindersCache(profile);
		cacheUniqueFindersCache(profile);

		return profile;
	}

	protected Profile toUnwrappedModel(Profile profile) {
		if (profile instanceof ProfileImpl) {
			return profile;
		}

		ProfileImpl profileImpl = new ProfileImpl();

		profileImpl.setNew(profile.isNew());
		profileImpl.setPrimaryKey(profile.getPrimaryKey());

		profileImpl.setId(profile.getId());
		profileImpl.setEmail(profile.getEmail());
		profileImpl.setUserId(profile.getUserId());
		profileImpl.setAssociateId(profile.getAssociateId());
		profileImpl.setFirstName(profile.getFirstName());
		profileImpl.setLastName(profile.getLastName());
		profileImpl.setSummary(profile.getSummary());
		profileImpl.setYearOfExperience(profile.getYearOfExperience());
		profileImpl.setExperience(profile.getExperience());
		profileImpl.setPrimarySkills(profile.getPrimarySkills());
		profileImpl.setSecondarySkills(profile.getSecondarySkills());
		profileImpl.setClientName(profile.getClientName());
		profileImpl.setEducation(profile.getEducation());

		return profileImpl;
	}

	/**
	 * Returns the profile with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the profile
	 * @return the profile
	 * @throws com.trinet.model.NoSuchProfileException if a profile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProfileException, SystemException {
		Profile profile = fetchByPrimaryKey(primaryKey);

		if (profile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProfileException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return profile;
	}

	/**
	 * Returns the profile with the primary key or throws a {@link com.trinet.model.NoSuchProfileException} if it could not be found.
	 *
	 * @param email the primary key of the profile
	 * @return the profile
	 * @throws com.trinet.model.NoSuchProfileException if a profile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile findByPrimaryKey(String email)
		throws NoSuchProfileException, SystemException {
		return findByPrimaryKey((Serializable)email);
	}

	/**
	 * Returns the profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the profile
	 * @return the profile, or <code>null</code> if a profile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Profile profile = (Profile)EntityCacheUtil.getResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
				ProfileImpl.class, primaryKey);

		if (profile == _nullProfile) {
			return null;
		}

		if (profile == null) {
			Session session = null;

			try {
				session = openSession();

				profile = (Profile)session.get(ProfileImpl.class, primaryKey);

				if (profile != null) {
					cacheResult(profile);
				}
				else {
					EntityCacheUtil.putResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
						ProfileImpl.class, primaryKey, _nullProfile);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ProfileModelImpl.ENTITY_CACHE_ENABLED,
					ProfileImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return profile;
	}

	/**
	 * Returns the profile with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param email the primary key of the profile
	 * @return the profile, or <code>null</code> if a profile with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Profile fetchByPrimaryKey(String email) throws SystemException {
		return fetchByPrimaryKey((Serializable)email);
	}

	/**
	 * Returns all the profiles.
	 *
	 * @return the profiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Profile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.trinet.model.model.impl.ProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of profiles
	 * @param end the upper bound of the range of profiles (not inclusive)
	 * @return the range of profiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Profile> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the profiles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.trinet.model.model.impl.ProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of profiles
	 * @param end the upper bound of the range of profiles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of profiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Profile> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<Profile> list = (List<Profile>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PROFILE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROFILE;

				if (pagination) {
					sql = sql.concat(ProfileModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Profile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Profile>(list);
				}
				else {
					list = (List<Profile>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the profiles from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Profile profile : findAll()) {
			remove(profile);
		}
	}

	/**
	 * Returns the number of profiles.
	 *
	 * @return the number of profiles
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROFILE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
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
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the profile persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.trinet.model.model.Profile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Profile>> listenersList = new ArrayList<ModelListener<Profile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Profile>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ProfileImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_PROFILE = "SELECT profile FROM Profile profile";
	private static final String _SQL_SELECT_PROFILE_WHERE = "SELECT profile FROM Profile profile WHERE ";
	private static final String _SQL_COUNT_PROFILE = "SELECT COUNT(profile) FROM Profile profile";
	private static final String _SQL_COUNT_PROFILE_WHERE = "SELECT COUNT(profile) FROM Profile profile WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "profile.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Profile exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Profile exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ProfilePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id"
			});
	private static Profile _nullProfile = new ProfileImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Profile> toCacheModel() {
				return _nullProfileCacheModel;
			}
		};

	private static CacheModel<Profile> _nullProfileCacheModel = new CacheModel<Profile>() {
			@Override
			public Profile toEntityModel() {
				return _nullProfile;
			}
		};
}