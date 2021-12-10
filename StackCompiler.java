public class StackCompiler
{
    public static void main(String[] args)
    {
        Stack<Double> valueStack = new Stack<Double>();
        Stack<String> operatorStack = new Stack<String>();
        
        String code = args[0];
        
        for (int i = 0; i<code.length(); i+=0)
        {
            String c = readString(i, code);
            i += c.length();
            if (c.equals("(")) 
            { 
                if (c.equals(")"))// Right Par
                {
                    if (operatorStack.isEmpty())
                    {
                        System.out.println("Invalid intstructions");
                        return;
                    }
                    if (valueStack.isEmpty())
                    {
                        System.out.println("Invalid instructions");
                        return;
                    }
               
                    String op = operatorStack.pop();
               
                    double num2 = valueStack.pop(); //Takes the top, so 2nd is first
                    double num1 = valueStack.pop();
               
                    double total = preformOperation(num1, num2, op.charAt(0));
                    valueStack.push(total);
                } else if (isNum(c.charAt(0))) //Is a number
                {
                    valueStack.push(Double.parseDouble(c));
                } else if (isOperator(c.charAt(0))) //Is an operator
                {
                    operatorStack.push(c);
                }
            }
        }
        if (!valueStack.isEmpty())
        {
            System.out.println(valueStack.pop());
        }
    }
    
    public static String readString(int pos, String code)
    {
        String message = "";
        message += code.charAt(pos);
        if (isOperator(code.charAt(pos)))
        {
          return message;
        }
        
        pos++;
        while(pos < code.length() && isNum(code.charAt(pos)))
        {
            message += code.charAt(pos);
            pos++;
        }
        return message;
    }
    
    public static double preformOperation(double n1, double n2, char op)
    {
        if (op == '+')
        {
            return n1+n2;
        }else if (op == '-')
        {
            return n1-n2;
        }else if (op == '/')
        {
            return n1/n2;
        }else
        {
            return n1*n2;
        }
    }
    
    public static boolean isNum(char c)
    {
        int unicode = (int) c;
        
        return unicode > 47 && unicode < 58;
    }
    
    public static boolean isOperator(char c)
    {
        int cI = (int) c;
        return (cI == 42) || (cI == 43) || (cI == 45) || (cI == 47);
    }
}
