package `z-algorithm`

class ZAlgorithm {

    fun getZArray(s: String): IntArray {
        val n = s.length

        // 문자열 크기의 Z 배열을 사용합니다.
        val zArray = IntArray(n)

        // 선형 시간으로 구성하기 위해 두 개의 포인터 Left와 Right로 접두사 부분 문자열의 간격을 나타냅니다.
        var left = 0
        var right = 0

        // 0번째 인덱스는 문자열 전체가 접두사이기 때문에 탐색은 1부터 시작합니다.
        for (i in 1 until n) {

            // i가 Right 포인터보다 크다면, i는 두 포인터 사이의 간격(즉, 접두사 부분 문자열) 밖에 있습니다.
            // 그렇기 때문에 i 이전에 시작하고 i 이후에 끝나는 접두사 부분 문자열이 없습니다. 즉, 현재의 두 포인터 사이의 간격에서 얻을 수 있는 정보가 없습니다.
            if (i > right) {

                // 현재의 포인터 값들은 쓸모가 없기 때문에, Left와 Right를 i로 초기화합니다.
                left = i
                right = i

                // i만큼의 간격을 유지하며 Left와 Right가 가리키는 값이 다를 때까지 (즉, 더 이상 접두사 부분 문자열이 아닐 때까지) 간격을 확장합니다.
                while (right < n && s[right - left] == s[right]) {
                    right++
                }

                // Z[i]를 두 포인터 사이의 간격(접두사 부분 문자열의 길이)으로 설정합니다.
                zArray[i] = right-- - left
            } else {
                // i가 Right 포인터보다 작거나 같다면, 여기부터는 두 가지 경우가 있습니다.
                val k = i - left

                // 만약 k부터 시작하는 접두사 부분 문자열의 길이가 현재 간격보다 짧다면, 확장할 수 있는 최대 간격이 현재 간격보다 짧다는 것을 의미합니다.
                // 따라서 i로부터 시작하는 접두사 부분 문자열의 길이는 최대 k이기 때문에  Z[i] = Z[k]로 설정하고, 간격은 동일하게 유지한 채로 다음 반복으로 넘어갑니다.
                if (zArray[k] < right - i + 1) {
                    zArray[i] = zArray[k]
                } else {
                    // 현재 간격보다 크다면, 현재 간격을 더 확장할 수 있다는 것을 의미합니다. 따라서 위의 코드처럼 간격을 확장하고 Z[i]를 설정합니다.
                    left = i

                    while (right < n && s[right - left] == s[right]) {
                        right++
                    }

                    zArray[i] = right-- - left
                }
            }
        }

        return zArray
    }
}
