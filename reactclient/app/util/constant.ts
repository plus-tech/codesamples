/* Root endpoint of the REST APIs */
export const RestEndpoint = 'http://localhost:9090/rest';
// export const RestEndpoint = 'https://localhost:8443/rest';
export const ApiKeyHeader = "Api-Key";
export const ApiAccessKey = "XvcItqrOmhPb2gxalIiGWhe7kOQ5cT6E5ZpUpYLM8RU="

export const PathFindAllDpts = '/findalldpts';
export const PathFindDptById = '/finddptbyid';
export const PathInsertDpt = '/insertdpt';
export const PathUpdateDpt = '/updatedpt';
export const PathDeleteDpt = '/deletedpt';


export const MenuItemText = {
  default: "",
  home: "Home",
  dpt:  "Department",
};

export const MenuItemPath = {
  default: "/",
  home: "/home",
  dpt: "/dpt",
};
