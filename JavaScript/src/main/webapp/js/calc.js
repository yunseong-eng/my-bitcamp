//빈 객체로 생성
let calc = {}

//멤버변수 추가
calc.x = 0;
calc.y = 0;

//멤버함수(자바 메서드)
calc.setValue = function(x, y){
	this.x = x;
	this.y = y;
}

calc.plus = function() {
	return this.x + this.y;
}

calc.minus = function() {
	return this.x - this.y;	
}

calc.result = function() {
	let sum = this.plus();
	let sub = this.minus();
	
	document.write("<div>" + sum + "</div>")
	document.write("<div>" + sub + "</div>")
}

/*
@객체 생성
Calc calc = new Calc();
calc.x = 25;

@클래스 선언
public class Calc {
	public int x = 25;

	public void setValue(int x, int y){
		this.x = x;
		this.y = y;
         }
	public void plus() {
		return x + y;
	}
	public void minus() {
		return x - y;
        }
	public void result() {
		int sum = plus();
		int sum = minus();
		
		System.out.println("덧셈 = " + sum);
		System.out.println("뺄셈 = " + sub);
       }

}

*/

