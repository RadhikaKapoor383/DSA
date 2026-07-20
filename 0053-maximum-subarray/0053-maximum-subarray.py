class Solution:
    def maxSubArray(self, nums):
        def helper(lo, hi):
            if lo == hi:
                return nums[lo]

            mid = (lo + hi) // 2

            left_max = helper(lo, mid)
            right_max = helper(mid + 1, hi)

            left_cross = float('-inf')
            s = 0
            for i in range(mid, lo - 1, -1):
                s += nums[i]
                if s > left_cross:
                    left_cross = s

            right_cross = float('-inf')
            s = 0
            for i in range(mid + 1, hi + 1):
                s += nums[i]
                if s > right_cross:
                    right_cross = s

            cross_max = left_cross + right_cross

            return max(left_max, right_max, cross_max)

        return helper(0, len(nums) - 1)