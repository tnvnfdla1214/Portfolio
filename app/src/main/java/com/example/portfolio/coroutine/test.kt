class LimitedNetworkUserRepository(
    private val api: UserApi,
) {
    // 동시 요청을 10개로 제한합니다.
    private val semaphore = Semaphore(10)

    suspend fun requestUser(userId: String) = semaphore.withPermit {
        api.requestUser(userId)
    }
}