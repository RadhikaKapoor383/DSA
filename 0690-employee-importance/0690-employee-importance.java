/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

import java.util.List;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int getImportance(List<Employee> employees, int id) {
        // Build a map from employee id -> Employee object for O(1) lookup
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee emp : employees) {
            employeeMap.put(emp.id, emp);
        }

        return dfs(employeeMap, id);
    }

    private int dfs(Map<Integer, Employee> employeeMap, int id) {
        Employee emp = employeeMap.get(id);
        if (emp == null) {
            return 0;
        }

        int totalImportance = emp.importance;
        for (int subordinateId : emp.subordinates) {
            totalImportance += dfs(employeeMap, subordinateId);
        }

        return totalImportance;
    }
}