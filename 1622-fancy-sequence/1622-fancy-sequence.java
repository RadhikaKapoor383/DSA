class Fancy {
    private static final long MOD = 1_000_000_007L;
    
    private long[] val;
    private long[] mul;   // snapshot of M at append time
    private long[] add;   // snapshot of A at append time
    private int size;
    
    private long curMul;
    private long curAdd;
    
    public Fancy() {
        val = new long[100001];
        mul = new long[100001];
        add = new long[100001];
        size = 0;
        curMul = 1;
        curAdd = 0;
    }
    
    public void append(int v) {
        val[size] = v % MOD;
        mul[size] = curMul;
        add[size] = curAdd;
        size++;
    }
    
    public void addAll(int inc) {
        curAdd = (curAdd + inc) % MOD;
    }
    
    public void multAll(int m) {
        curMul = (curMul * m) % MOD;
        curAdd = (curAdd * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= size) return -1;
        
        long v0 = ((val[idx] - add[idx]) % MOD + MOD) % MOD;
        v0 = (v0 * modInverse(mul[idx], MOD)) % MOD;
        
        long ans = (curMul * v0 + curAdd) % MOD;
        return (int) ans;
    }
    
    private long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod); // Fermat's Little Theorem (mod is prime)
    }
    
    private long modPow(long base, long exp, long mod) {
        base %= mod;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */