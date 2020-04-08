class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{
    
    @Override
    public void leggTil(T x){
        if (start == null){
            start = new Node(x);
        }
        else if(x.compareTo(start.data) <= 0){
            Node node = new Node(x);
            node.neste = start;
            start = node;
        }
        else{
            Node node = new Node(x);
            Node ref = start;
            Node forrige = null;
            for(int i = 0; i < iBruk; i++){
                if(x.compareTo(ref.data) > 0){
                    if(ref.neste != null){
                        forrige = ref;
                        ref = ref.neste;
                    }
                    else{
                        ref.neste = node;
                        break;
                    }
                }
                else{
                    node.neste = ref;  
                    forrige.neste = node; 
                    break;
                }
            }
        }
        iBruk++;
    }

    @Override 
    public T fjern(){
        return fjern(iBruk-1);
    }

    @Override
    public void sett(int pos, T x){
        throw new UnsupportedOperationException();
    }
    @Override
    public void leggTil(int pos, T x){
        throw new UnsupportedOperationException();
    }
}

