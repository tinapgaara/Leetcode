package square;

/**
 * Created by yingtan on 10/18/17.
 *
 * 好几个人一起付账，应该平分的，大家付了不一样的钱，怎么样move money
 *
 * 按照付钱从少到多将所有人排序，让最少付的人（在队列左边）首先将余额xxx付给付钱最多的人（在右边），
 * 这个付钱最多的人最多只能拿它已经付的多于平均数的钱，付钱最多的人拿到差额后离开队列。
 * 如果xxx还有剩余，则将xxx的剩余部分付给新的队列右边的人。以此类推。左边的人付完平均数以后离开队列
 *
 * http://www.geeksforgeeks.org/minimize-cash-flow-among-given-set-friends-borrowed-money/
 */
public class MinCashFlow {
}
