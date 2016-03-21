/**
 * Created by desuev on 21.03.16.
 */
public class STACK
{
    int[] position, quantity;
    int pointerPos, pointerQuant, counter;
    boolean created = false;
    STACK()
    {
        pointerPos = pointerQuant = -1; created = true;
    }

    int getDephth()
    {
        return pointerPos;
    }

    void createStack(int count)
    {
        counter = count;
        position = new int[count*2];
        quantity = new int[count*2];
    }

    public STACK clone() throws CloneNotSupportedException {
    return (STACK)super.clone();
    }

    void add(int pos, int quant)
    {
        if(pointerPos == counter || pointerQuant == counter)
        {
            System.out.print("Стек забит.");
            return;
        }
        else {
            position[++pointerPos] = pos;
            quantity[++pointerQuant] = quant;
        }
    }

    void setHead()
    {
      while(position[++pointerPos] != 0)
          pointerQuant = pointerPos;
    }

    int popPosition()
    {
        if(pointerPos < 0)
        {
            return 0;
        }
        else
        {
            return  position[pointerPos--];
        }
    }

    int popQuantity()
    {
        if(pointerQuant < 0)
        {
            return 0;
        }
        else
        {
            return  quantity[pointerQuant--];
        }
    }
}
