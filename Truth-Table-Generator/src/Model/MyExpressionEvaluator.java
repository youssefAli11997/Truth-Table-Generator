package Model;

import Interfaces.ExpressionEvaluator;

import javax.management.RuntimeErrorException;
import java.util.Stack;

public class MyExpressionEvaluator implements ExpressionEvaluator {

	@Override
	public Boolean getTruthValue(String expression) {
		return evaluate(infixToPostfix(expression));
	}

	@Override
	public String infixToPostfix(String expression) {
		if(expression == null || expression.isEmpty())
		{
			throw null;
		}
		else
		{
			for(int i = 1; i < expression.length(); i++)
			{
				char currentMember = expression.charAt(i);
				char prevMember = expression.charAt(i-1);
				if((currentMember == '~' || currentMember  == '^' || currentMember == 'v' || currentMember == '>' || currentMember == '<') &&
				   (prevMember == '~' || prevMember  == '^' || prevMember == 'v' || prevMember == '>' || prevMember == '<'))
				{
					if(currentMember != '~'){
						throw new RuntimeErrorException(null);
					}
				}
			}
			Stack signs = new Stack();
			Stack operands = new Stack();
			for(int i = 0; i < expression.length(); i++)
			{
				char chars = expression.charAt(i);
				if(chars != '~' && chars != '^' && chars != 'v' && chars != '>' && chars != '<')
				{
					if(chars == '(')
						signs.push(chars);
					else if(chars == ')')
					{
						while(!signs.peek().equals('('))
						{
							operands.push(signs.pop());
						}
						signs.pop();
					}
					else
						operands.push(chars);
				}
				else
				{

					if((signs.isEmpty() ) || signs.peek().equals('('))
					{
						if(!(signs.isEmpty()) && signs.peek().equals(chars))
						{
							throw null;
						}
						signs.push(chars);
					}
					else
					{
						char sign = (char) signs.peek();
						if(chars == ' ')
						{
							continue;
						}
						else
						{
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////							
							if(chars == '~' )
							{
								signs.push(chars);
							}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////							
							else if(chars == '^' && !(sign == '^'))
							{
								if(sign == 'v' || sign == '>' || sign == '<')
								{
									signs.push(chars);
								}
								else{
									int flag=0;
									do{
										operands.push(signs.pop());
										if(!signs.isEmpty())
										{
											sign = (char) signs.peek();
										}
										else
											flag++;
										
									}while((chars == '^') && (sign == '~' && flag == 0));
									
									signs.push(chars);
									flag = 0;
								}
							}
							else if((chars == '^') && (sign == '^'))
							{
								int flag=0;
								do{
									operands.push(signs.pop());
									if(!signs.isEmpty())
									{
										sign = (char) signs.peek();
									}
									else
										flag++;
									
								}while((chars == '^') && (sign == '^') && flag == 0);
								
								signs.push(chars);
								flag = 0;
							}
////////////////////////////////////////////////////////////////////////////////////////////
							else if(chars == 'v' && !(sign == 'v'))
							{
								if(sign == '>' || sign == '<')
								{
									signs.push(chars);
								}
								else{
									int flag=0;
									do{
										operands.push(signs.pop());
										if(!signs.isEmpty())
										{
											sign = (char) signs.peek();
										}
										else
											flag++;
										
									}while((chars == 'v') && ((sign == '^' || sign == '~') && flag == 0));
									
									signs.push(chars);
									flag = 0;
								}
							}
							else if((chars == 'v') && (sign == 'v'))
							{
								int flag=0;
								do{
									operands.push(signs.pop());
									if(!signs.isEmpty())
									{
										sign = (char) signs.peek();
									}
									else
										flag++;
									
								}while((chars == 'v') && (sign == 'v') && flag == 0);
								
								signs.push(chars);
								flag = 0;
							}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							else if(chars == '>' && !(sign == '>'))
							{
								if(sign == '<')
								{
									signs.push(chars);
								}
								else{
									int flag=0;
									do{
										operands.push(signs.pop());
										if(!signs.isEmpty())
										{
											sign = (char) signs.peek();
										}
										else
											flag++;
										
									}while((chars == '>') && ((sign == '^' || sign == '~' || sign == 'v') && flag == 0));
									
									signs.push(chars);
									flag = 0;
								}
							}
							else if((chars == '>') && (sign == '>'))
							{
								int flag=0;
								do{
									operands.push(signs.pop());
									if(!signs.isEmpty())
									{
										sign = (char) signs.peek();
									}
									else
										flag++;
									
								}while((chars == '>') && (sign == '>') && flag == 0);
								
								signs.push(chars);
								flag = 0;
							}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							else if((chars == '<'))
							{
								int flag=0;
								do{
									operands.push(signs.pop());
									if(!signs.isEmpty())
									{
										sign = (char) signs.peek();
									}
									else
										flag++;
									
								}while((chars == '<') && (flag == 0));
								
								signs.push(chars);
								flag = 0;
							}
							else if(chars == ')')
							{
								while(sign != '(')
								{
									operands.push(signs.pop());
									sign = (char) signs.peek();
								}
							}
						}
					
					}
				}
			}
			int counter = signs.size();
			for(int i = 1; i < counter+1; i++)
			{
				if(signs.peek().equals('(') || signs.peek().equals(')'))
					throw null;
				operands.push(signs.pop());
			}
			char[] result = new char[expression.length()];
					int l = operands.size()-1;
			for(int i = l; i >= 0; i--)
			{
				result[i] = (char) operands.pop();
			}
			String s = String.valueOf(result).trim();
			StringBuilder out = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
			   if (i > 0) {
			   		out.append(" ");
			   }

			   out.append(s.charAt(i));
			}
			return out.toString();
		}

	}

	@Override
	public Boolean evaluate(String expression) {
		System.out.println(expression);
		if(expression == null || expression.isEmpty())
		{
			throw null;
		}
		else
		{
			for(int i = 1; i < expression.length(); i++)
			{
				char currentMember = expression.charAt(i);
				char prevMember = expression.charAt(i-1);
				if((currentMember == '~' || currentMember  == '^' || currentMember == 'v' || currentMember == '>' || currentMember == '<') &&
				   (prevMember == '~' || prevMember  == '^' || prevMember == 'v' || prevMember == '>' || prevMember == '<'))
				{
					if(currentMember != '~'){
						throw new RuntimeErrorException(null);
					}
				}
			}
			Stack operands = new Stack();
			String[] splited = expression.split("");
			for(int i  = 0; i < splited.length; i++)
			{
				
				String chars = splited[i];
				if(chars.equals(" "))
				{
					continue;
				}
				else
				{
					if(!chars.equals("~")  && !chars.equals("^") && !chars.equals("v") && !chars.equals(">") && !chars.equals("<"))
					{
						operands.push(chars);
					}
					else
					{
						if(operands.size() == 1 && !chars.equals("~"))
						{
							throw null;
						}
						else
						{
							if(!chars.equals("~")){
								boolean num1 = true;
								boolean num2 = true;
								boolean booleanResult;
								int x =  Integer.parseInt(operands.pop().toString());
								int y =  Integer.parseInt(operands.pop().toString());
								if(y == 0){
									num1 = false;
								}
								if(x == 0){
									num2 = false;
								}
								System.out.println(num1 + " " + x + " " + num2 + " " + y);
								int result = 1;
								if(chars.equals("^"))
								{
									booleanResult = num1 & num2;
								}
								else if(chars.equals("v"))
								{
									booleanResult = num1 | num2;
								}
								else if(chars.equals(">"))
								{
									booleanResult = !(num1) | num2;
									System.out.println(!num1 + " " + num2 + " " + booleanResult);
								}
								else
								{
									booleanResult = (num1 & num2) | (!(num1) & !(num2));
								}
								if(booleanResult == false){
									result = 0;
								}
								operands.push(result);
							}
							else{
								int x = Integer.parseInt(operands.pop().toString());
								if(x == 1){
									x = 0;
								}
								else{
									x = 1;
								}
								operands.push(x);
							}
						}
					}
				}
			}
			
			if(operands.pop().toString().equals("1")){
				return true;
			}
			return false;
		}
	}

}
