// to do money formatting
import java.text.DecimalFormat;

public class MoneyFormatDemo
{
	public static void main(String[] args)
	{
		String money = "";
		DecimalFormat dollarFormat = new DecimalFormat("$#,###,###.00");
		System.out.println(dollarFormat.format(34456.2));
		money = dollarFormat.format(20043.44);
		System.out.println(money);
	}
}