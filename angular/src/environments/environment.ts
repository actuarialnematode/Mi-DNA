// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  serviceUrl: 'http://localhost:8081',
  loginEndpoint: '/login',
  registerEndpoint: '/register',
  checkEndpoint:'/checkSession',
  caseEndpoint: '/varfish/case/',
  allCasesEndpoint: '/varfish/case/all',
  doc2hpoEndpoint: '/doc2hpo',
  phen2geneEndpoint: '/phen2gene',
  iemEndpoint: '/iembase',
  omimEndpoint: '/omim',
  isLoading: false
};