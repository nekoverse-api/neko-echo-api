# Basic Nginx Setup with SSL

### Requeriments 

- Domain (Route 53)
- EC2 Instance 

## Install Docker & Nginx

**Connect to EC2 instance**

```sh
ssh -i "something.pem" ec2-user@ec2-10.0.0.1.compute-1.amazonaws.com
```

**Install Docker**

```sh
sudo yum update -y
sudo yum install -y docker
sudo service docker start
sudo usermod -a -G docker ec2-user
```

**Exit from instance to have docker without sudo**

```sh
exit
```

**Connect to EC2 instance - Again**

```sh
ssh -i "something.pem" ec2-user@ec2-10.0.0.1.compute-1.amazonaws.com
```

**Test Docker CLI**

```sh
docker ps
```

Install Nginx

```sh
sudo dnf install nginx
sudo systemctl enable nginx.service
sudo systemctl start nginx.service
```

Test Nginx Service 

```sh
sudo systemctl status nginx.service
```

## Install Certbot & Let's Encrypt 

**Configure Domain**

```
Add an A record into your domain with the IP of EC2 Instance
point is VERY IMPORTANT to generate certificate
```

**Install Certbot and Nginx Plugin**

```sh
sudo dnf install certbot
sudo yum install python3-certbot-nginx
sudo certbot --nginx certonly
```

**Certbot requires some info from you, just fill with you info**

```
- email: test@something.com
- some questions just enter YES
- domain: my.customdomain.com
```

**Update Nginx Config**

```sh
sudo cp /etc/nginx/nginx.conf /etc/nginx/nginx.conf.bak
sudo nano /etc/nginx/nginx.conf
```

**Important Note**

- `nginx/nginx.conf` contains basic config, update according to your domain and requirements 
- Very Important: Update domain
- It redirects all the HTTP traffic to HTTPS

**Test Nginx Config & Restart Nginx**

```sh
sudo nginx -t
sudo systemctl restart nginx
```

**Test Nginx Service & Just open your domain in the browser**

```sh
sudo systemctl status nginx.service
```

## Home Page Details

```sh
cd /usr/share/nginx/html/
sudo curl --output favicon.ico https://home.nekoverse.me/favicon.ico
sudo nano index.html 
```

**Notes**

- Delete all content from index and copy `nginx/index.html`

## References

- https://fedoraproject.org/wiki/Nginx
- https://docs.aws.amazon.com/us_en/serverless-application-model/latest/developerguide/install-docker.html
- https://repost.aws/knowledge-center/linux-install-ssl-certificate-nginx
- https://stackoverflow.com/questions/53223914/issue-using-certbot-with-nginx
