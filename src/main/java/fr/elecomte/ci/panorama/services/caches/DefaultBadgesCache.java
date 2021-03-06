/*
 * Copyright 2016 Emmanuel Lecomte
 *  
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *  
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package fr.elecomte.ci.panorama.services.caches;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Basic BadgesCache using a ConcurrentHashMap to store uncompressed String representation
 * of badges
 * </p>
 * 
 * @author elecomte
 * @since 0.1.0
 */
public class DefaultBadgesCache implements BadgesCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBadgesCache.class);

	private Map<BadgeProjectId, BadgeRepository> projectBadgeRepositories = new ConcurrentHashMap<>();

	public DefaultBadgesCache() {

	}

	/**
	 * @param projectCode
	 * @param projectVersion
	 */
	@Override
	public void dropCache(String projectCode, String projectVersion) {

		LOGGER.debug("Drop from cache everything for project {}/{}", projectCode, projectVersion);

		this.projectBadgeRepositories.remove(new BadgeProjectId(projectCode, projectVersion));
	}

	/**
	 * @param projectCode
	 * @param projectVersion
	 * @param badgeIdentifier
	 * @return
	 */
	@Override
	public String getCachedBadge(String projectCode, String projectVersion, String badgeIdentifier) {

		BadgeProjectId id = new BadgeProjectId(projectCode, projectVersion);

		BadgeRepository repo = this.projectBadgeRepositories.get(id);

		if (repo == null) {
			LOGGER.debug("No cache entry found yet for project {}/{}", projectCode, projectVersion);
			return null;
		}

		String value = repo.getBadge(badgeIdentifier);

		if (LOGGER.isDebugEnabled()) {
			if (value != null) {
				LOGGER.debug("Found a cache entry for project {}/{}, badge {}, of {} caracters", projectCode, projectVersion,
						badgeIdentifier, Integer.valueOf(value.length()));
			} else {
				LOGGER.debug("No cache entry found for project {}/{}, badge {}", projectCode, projectVersion, badgeIdentifier);
			}
		}

		return value;
	}

	/**
	 * @param projectCode
	 * @param projectVersion
	 * @param badgeIdentifier
	 * @param badge
	 */
	@Override
	public void putCachedBadge(String projectCode, String projectVersion, String badgeIdentifier, String badge) {

		BadgeProjectId id = new BadgeProjectId(projectCode, projectVersion);

		BadgeRepository repo = this.projectBadgeRepositories.get(id);

		if (repo == null) {
			LOGGER.debug("No cache entry found yet for project {}/{}, create it", projectCode, projectVersion);
			repo = new BadgeRepository();
			this.projectBadgeRepositories.put(id, repo);
		}

		repo.keepBadge(badgeIdentifier, badge);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Added a cache entry for project {}/{}, badge {}, of {} caracters. Now cache size is {} entries with"
					+ " a total of {} caracters", projectCode, projectVersion, badgeIdentifier, Integer.valueOf(badge.length()),
					totalSize(), totalEntriesLengthSum());
		}
	}

	/**
	 * @return
	 */
	@Override
	public Integer totalSize() {
		return this.projectBadgeRepositories.values().stream().collect(Collectors.summingInt(BadgeRepository::size));
	}

	/**
	 * @return
	 */
	@Override
	public Integer totalEntriesLengthSum() {
		return this.projectBadgeRepositories.values().stream().collect(Collectors.summingInt(BadgeRepository::getEntriesLengthSum));
	}

	/**
	 * 
	 */
	@Override
	public void dropAll() {
		this.projectBadgeRepositories.clear();
	}

	/**
	 * Holder of uncompressed badge string, in ConcurrentHashMap
	 * 
	 * @author elecomte
	 * @since 0.1.0
	 */
	private static class BadgeRepository {

		private Map<String, String> badges = new ConcurrentHashMap<>();

		BadgeRepository() {
			super();
		}

		/**
		 * @param badgeIdentifier
		 * @return
		 */
		String getBadge(String badgeIdentifier) {
			return this.badges.get(badgeIdentifier);
		}

		/**
		 * @param badgeIdentifier
		 * @param badge
		 */
		void keepBadge(String badgeIdentifier, String badge) {
			this.badges.put(badgeIdentifier, badge);
		}

		/**
		 * @return
		 */
		Integer getEntriesLengthSum() {
			return this.badges.entrySet().stream().collect(Collectors.summingInt(e -> e.getKey().length() + e.getValue().length()));
		}

		/**
		 * @return
		 */
		int size() {
			return this.badges.size();
		}
	}

	/**
	 * Cache id
	 * 
	 * @author elecomte
	 * @since 0.1.0
	 */
	private static class BadgeProjectId {

		private final int hash;

		/**
		 * @param projectCode
		 * @param projectVersion
		 */
		BadgeProjectId(String projectCode, String projectVersion) {
			this.hash = projectCode.hashCode() + 37 * projectVersion.hashCode();
		}

		/**
		 * @return
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return this.hash;
		}

		/**
		 * @param obj
		 * @return
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BadgeProjectId other = (BadgeProjectId) obj;
			if (this.hash != other.hash)
				return false;
			return true;
		}

	}

}
