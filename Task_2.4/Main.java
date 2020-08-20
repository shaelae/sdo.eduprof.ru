public class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int sum = nums[0];
        for (int i=0; i<nums.length; i++) {
            System.out.println(sum);
            if (i<nums.length-1) sum += nums[i + 1];
        }
    }
}
