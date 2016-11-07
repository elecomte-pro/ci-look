package fr.elecomte.ci.look.services.badges;

import fr.elecomte.ci.look.services.badges.providers.BadgeValueProvider;
import fr.elecomte.ci.look.services.badges.providers.ProjectVersionProvider;
import fr.elecomte.ci.look.services.badges.providers.ResultSuccessProvider;
import fr.elecomte.ci.look.services.badges.providers.TestResultCountProvider;

/**
 * All supported badge types
 * 
 * @author elecomte
 * @since 0.1.0
 */
public enum BadgeType {

	BUILD("build.svg", new ResultSuccessProvider()),
	TEST("test.svg", new ResultSuccessProvider()),
	TEST_COUNT("test-count.svg", new TestResultCountProvider()),
	VERSION("version.svg", new ProjectVersionProvider()),
	VERSION_PENDING("version-pending.svg", new ProjectVersionProvider()),
	VERSION_RELEASED("version-released.svg", new ProjectVersionProvider());

	private final String badgeFile;
	private final BadgeValueProvider<?> provider;

	/**
	 * @param badgeFile
	 * @param title
	 * @param titleColor
	 * @param provider
	 */
	private BadgeType(String badgeFile, BadgeValueProvider<?> provider) {
		this.badgeFile = badgeFile;
		this.provider = provider;
	}

	/**
	 * @return the badgeFile
	 */
	public String getBadgeFile() {
		return this.badgeFile;
	}

	/**
	 * @return the provider
	 */
	@SuppressWarnings("unchecked")
	public <T> BadgeValueProvider<T> getProvider() {
		return (BadgeValueProvider<T>) this.provider;
	}
}