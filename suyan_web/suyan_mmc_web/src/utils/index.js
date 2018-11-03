/**
 * 格式化时间
 */
export function dateFormat(date,fmt = "YYYY-MM-DD HH:mm:ss") {
  let moment = require("moment");
  return moment(date).format(fmt);

}


/**
 * 是否有权限
 * @param {*} key
 */
export function isAuth(key) {
  var isAdmin = sessionStorage.getItem('isAdmin');
  if (isAdmin && isAdmin === 'true') {
    return true;
  }
  return JSON.parse(sessionStorage.getItem('permissions') || '[]').indexOf(key) !== -1 || false
}


/**
 * 树形数据转换
 * @param {*} data
 * @param {*} id
 * @param {*} pid
 */
export function treeDataTranslate(data, id = 'id', pid = 'parentId') {
  var res = []
  var temp = {}
  for (var i = 0; i < data.length; i++) {
    temp[data[i][id]] = data[i]
  }
  for (var k = 0; k < data.length; k++) {
    if (temp[data[k][pid]] && data[k][id] !== data[k][pid]) {
      if (!temp[data[k][pid]]['children']) {
        temp[data[k][pid]]['children'] = []
      }
      if (!temp[data[k][pid]]['_level']) {
        temp[data[k][pid]]['_level'] = 1
      }
      data[k]['_level'] = temp[data[k][pid]]._level + 1
      temp[data[k][pid]]['children'].push(data[k])
    } else {
      res.push(data[k])
    }
  }
  return res
}
