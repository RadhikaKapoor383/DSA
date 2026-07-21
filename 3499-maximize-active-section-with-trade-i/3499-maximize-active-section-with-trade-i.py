class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        t = '1' + s + '1'
        n = len(t)

        ones = []  
        zeros = []  
        i = 0
        while i < n:
            j = i
            while j < n and t[j] == t[i]:
                j += 1
            length = j - i
            if t[i] == '1':
                ones.append(length)
            else:
                zeros.append(length)
            i = j

        total_ones = sum(ones) - 2

        K = len(ones) - 1 
        best_gain = 0

        if K >= 2 and zeros:

            top_idx = sorted(range(len(zeros)), key=lambda k: -zeros[k])[:3]

            for idx1 in range(1, K):
                left = idx1 - 1
                right = idx1
                dl = zeros[left]
                dr = zeros[right]

                merged_gain = dl + dr 

                other_gain = float('-inf')
                for k in top_idx:
                    if k != left and k != right:
                        other_gain = zeros[k] - ones[idx1]
                        break

                best_gain = max(best_gain, merged_gain, other_gain)

        return total_ones + best_gain