class Expression{
    int x1;
    int x2;
    Expression(int x1, int x2){
        this.x1 = x1;
        this.x2 = x2;
    }
    public int Multiply(){
        double log1 = Math.log10(x1);
        double log2 = Math.log10(x2);
        return (int)Math.pow(10, log1+log2);
    }
}