
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'auth.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'auth.UserRole'
grails.plugin.springsecurity.authority.className = 'auth.Role'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.requestMap.className = 'auth.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'

// @Mamun // 2020-12-15
// Customize Security
grails.plugin.springsecurity.useSecurityEventListener = true


grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/icon/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],

	[pattern: '/**/img/**',      access: ['permitAll']],
	[pattern: '/multimedia/*', access: ['permitAll']],
	[pattern: '/user/*', access: ['permitAll']],
	[pattern: '/api/user/*', access: ['permitAll']],
	[pattern: '/product/*', access: ['permitAll']],
	[pattern: '/api/products/*', access: ['permitAll']],
	[pattern: '/serviceItems/*', access: ['permitAll']],
	[pattern: '/api/serviceItems/*', access: ['permitAll']],
	[pattern: '/serviceOrders/*', access: ['permitAll']],
	[pattern: '/api/serviceOrders/*', access: ['permitAll']],
	[pattern: '/reqServiceMaterials/*', access: ['permitAll']],
	[pattern: '/api/reqServiceMaterials/*', access: ['permitAll']],
	[pattern: '/favoriteTechnician/*', access: ['permitAll']],
	[pattern: '/api/favoriteTechnician/*', access: ['permitAll']],
	[pattern: '/salesOrder/*', access: ['permitAll']],
	[pattern: '/api/salesOrder/*', access: ['permitAll']],
	[pattern: '/userFeedbacks/*', access: ['permitAll']],
	[pattern: '/api/userFeedbacks/*', access: ['permitAll']],
	[pattern: '/offerPromos/*', access: ['permitAll']],
	[pattern: '/api/offerPromos/*', access: ['permitAll']],
	[pattern: '/prodServicePriceRateList/*', access: ['permitAll']],
	[pattern: '/api/prodServicePriceRateList/*', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/icon/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

