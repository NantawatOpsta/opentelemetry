from locust import HttpUser, task


class LoadTest(HttpUser):

    @task
    def web_load_test(self):
        self.client.get("/")
        self.client.get("/error")
