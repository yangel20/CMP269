from abc import ABC, abstractmethod


class Payable(ABC):

    @abstractmethod
    def process_payment(self, amount: float):
        pass

    @abstractmethod
    def get_payment_status(self) -> str:
        pass


class PaymentMethod(Payable, ABC):

    total_transactions = 0 

    def __init__(self, account_holder: str, balance: float):
        self.account_holder = account_holder
        self.balance = balance

    @abstractmethod
    def validate_account(self):
        pass


class CreditCard(PaymentMethod):

    def __init__(self, account_holder: str, balance: float, credit_limit: float):
        super().__init__(account_holder, balance)
        self.credit_limit = credit_limit
        self.validate_account()

    def validate_account(self):
        if self.credit_limit < 0:
            self.credit_limit = 0

    def process_payment(self, amount: float):
        if amount > self.balance + self.credit_limit:
            print("Transaction Declined.")
        else:
            self.balance -= amount
            PaymentMethod.total_transactions += 1

    def get_payment_status(self) -> str:
        return (f"CreditCard - Holder: {self.account_holder}"
                f", Balance: {self.balance}"
                f", Credit Limit: {self.credit_limit}")


class MealPlan(PaymentMethod):

    def __init__(self, account_holder: str, balance: float):
        super().__init__(account_holder, balance)
        self.status = "Pending"
        self.validate_account()

    def validate_account(self):
        if self.balance < 0:
            print("Balance can't be negative. Setting to 0.")
            self.balance = 0

    def process_payment(self, amount: float):
        if amount <= 0:
            self.status = "Invalid Amount"
            print("Transaction Declined.")
            return

        if amount > self.balance:
            self.status = "Declined"
            print("Transaction Declined.")
        else:
            self.balance -= amount
            PaymentMethod.total_transactions += 1
            self.status = "Approved"

    def get_payment_status(self) -> str:
        return f"MealPlan [{self.account_holder}] Status: {self.status}, Balance: {self.balance}"


# Main
if __name__ == "__main__":
    payment_queue: list[Payable] = [
        CreditCard("Student cc", 100.0, 200.0),
        MealPlan("Student mp", 100.0),
    ]

    for p in payment_queue:
        p.process_payment(50.0)
        print("Status:", p.get_payment_status())

    print("Total Transactions:", PaymentMethod.total_transactions)