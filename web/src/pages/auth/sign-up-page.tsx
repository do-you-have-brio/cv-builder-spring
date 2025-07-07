import { signUpSchema, type SignUpSchema } from "@/@types/schemas/auth";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input, SecureInput } from "@/components/ui/input";
import { useSignUp } from "@/hooks/auth/use-sign-up";
import { zodResolver } from "@hookform/resolvers/zod";
import { LoaderCircleIcon } from "lucide-react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";

export const SignUpPage = () => {
  const navigate = useNavigate();
  const { isPending, mutateAsync } = useSignUp();

  const form = useForm<SignUpSchema>({
    resolver: zodResolver(signUpSchema),
    defaultValues: {
      name: "",
      email: "",
      password: "",
    },
  });

  const handleFormSubmit = async (values: SignUpSchema) => {
    const response = await mutateAsync(values);
    console.log({ response });
    if (!response) return;
    
    navigate("/auth/sign-in", {
      state: { email: values.email },
      replace: true,
    });
  };

  return (
    <Card className="w-full max-w-sm">
      <CardHeader>
        <CardTitle>Sign up for an account</CardTitle>
        <CardDescription>
          Enter your details below to create a new account
        </CardDescription>
      </CardHeader>

      <CardContent className="flex flex-col gap-4">
        <Form {...form}>
          <form
            className="flex flex-col gap-4"
            onSubmit={form.handleSubmit(handleFormSubmit)}
          >
            <FormField
              control={form.control}
              name="name"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Name</FormLabel>
                  <FormControl>
                    <Input placeholder="John Doe" {...field} />
                  </FormControl>
                  <FormDescription />
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="email"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Email</FormLabel>
                  <FormControl>
                    <Input placeholder="john@doe.com" {...field} />
                  </FormControl>
                  <FormDescription />
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="password"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Password</FormLabel>
                  <FormControl>
                    <SecureInput placeholder="Password" {...field} />
                  </FormControl>
                  <FormDescription />
                  <FormMessage />
                </FormItem>
              )}
            />
            <FormField
              control={form.control}
              name="confirmPassword"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Password</FormLabel>
                  <FormControl>
                    <SecureInput placeholder="Confirm Password" {...field} />
                  </FormControl>
                  <FormDescription />
                  <FormMessage />
                </FormItem>
              )}
            />

            <Button disabled={isPending} type="submit" className="w-full">
              {isPending && <LoaderCircleIcon className="animate-spin" />}
              <>Sign Up</>
            </Button>
          </form>
        </Form>

        <div className="flex items-center justify-center">
          <span>Already have an account?</span>
          <Button type="button" className="p-2" variant="link" asChild>
            <Link to="/auth/sign-in">Sign in</Link>
          </Button>
        </div>
      </CardContent>
    </Card>
  );
};
