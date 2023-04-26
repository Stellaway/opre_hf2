import java.util.ArrayList;

public class SecondChance {
    ArrayList<Integer> swap;
    ArrayList<Frame> fifo;
    int fault;

    public SecondChance(ArrayList<Integer> inputSwap){
        this.swap = new ArrayList<>();
        this.swap.addAll(inputSwap);
        fifo = new ArrayList<>();
        fifo.add(new Frame('A'));
        fifo.add(new Frame('B'));
        fifo.add(new Frame('C'));
        fault = 0;
    }

    public void run(){
        while(swap.size()>0){
            int page = swap.remove(0);

            int fifoNum = isPageInFifo(page);
            if(fifoNum!=-1){
                fifo.get(fifoNum).setUsed(true);
                fifo.get(fifoNum).releaseFrozen();
                System.out.print('-');
                reduceFreeze();
                continue;
            }

            fault++;

            Frame toPut = getToPutFrame();

            if(toPut == null){
                System.out.print('*');
                reduceFreeze();
                continue;
            }

            reduceFreeze();

            toPut.resetFrozen();
            toPut.setPage(page);

            System.out.print(toPut.getLetter());

        }
        System.out.println("\n" + fault);
    }


    private void reduceFreeze(){
        for(Frame f : fifo)
            f.decrFrozen();
    }

    private int isPageInFifo(int p){
        for(int i = 0; i<fifo.size(); i++)
            if(fifo.get(i).isThisPage(p))
                return i;

        return -1;
    }


    //gets which frame should we put the page in
    private Frame getToPutFrame(){
        ArrayList<Frame> tempFifo = new ArrayList<>();  //for the cyclic-ness
        ArrayList<Frame> toRemove = new ArrayList<>();
        Frame toPutFrame = null;
        for(Frame frame : fifo){
            if(frame.isNotFrozen() &&!frame.isUsed()) {
                toPutFrame = frame;
                break;
            }
            if(frame.isNotFrozen()) {
                frame.setUsed(false);
                toRemove.add(frame);
                tempFifo.add(frame);
            }

        }
        fifo.removeAll(toRemove);
        fifo.remove(toPutFrame);
        fifo.addAll(tempFifo);
        if(toPutFrame != null) fifo.add(toPutFrame);


        if(toPutFrame == null) {
            for (Frame frame : fifo) {
                if (frame.isNotFrozen() && !frame.isUsed()) {
                    toPutFrame = frame;
                    break;
                }
            }
            fifo.remove(toPutFrame);
            if(toPutFrame != null) fifo.add(toPutFrame);
        }

        return toPutFrame;
    }


}
