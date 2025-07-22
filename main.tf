terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}
provider "aws" {
  region = "us-east-1" # ✅ Region
}
# 1. VPC
resource "aws_vpc" "my_vpc" {
  cidr_block = "10.0.0.0/16"
  tags = {
    Name = "MyVPC"
  }
}

# 2. Internet Gateway
resource "aws_internet_gateway" "my_igw" {
  vpc_id = aws_vpc.my_vpc.id
  tags = {
    Name = "MyIGW"
  }
}

# 3. Public Subnet
resource "aws_subnet" "my_subnet" {
  vpc_id                  = aws_vpc.my_vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"  # ✅ Match with region
  map_public_ip_on_launch = true
  tags = {
    Name = "MyPublicSubnet"
  }
}

# 4. Route Table
resource "aws_route_table" "my_route_table" {
  vpc_id = aws_vpc.my_vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.my_igw.id
  }
  tags = {
    Name = "MyRouteTable"
  }
}

# Associate Route Table with Subnet
resource "aws_route_table_association" "my_route_assoc" {
  subnet_id      = aws_subnet.my_subnet.id
  route_table_id = aws_route_table.my_route_table.id
}

# 5. Security Group (Allow SSH)
resource "aws_security_group" "allow_ssh" {
  name        = "allow_ssh"
  description = "Allow SSH access"
  vpc_id      = aws_vpc.my_vpc.id
  ingress {
    description = "SSH from anywhere"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    description = "Selenium Grid port"
    from_port   = 4444
    to_port     = 4444
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = "AllowSSH"
  }
}
#resource "aws_key_pair" "deployer" {
 # key_name      = "aws_key"
  #public_key = var.public_key
#}

data "aws_key_pair" "deployer" {
  key_name      = "aws_key"
  #public_key = var.public_key
}

# 6. EC2 Instance
resource "aws_instance" "my_ec2" {
  ami                         = "ami-001dd4635f9fa96b0" # ✅ Ubuntu Image
  instance_type               = "t2.medium"
  subnet_id                   = aws_subnet.my_subnet.id
  vpc_security_group_ids      = [aws_security_group.allow_ssh.id]
  associate_public_ip_address = true
  key_name                    = "aws_key" # ✅ AWS EC2 Key Pair name
  # Install Docker, Compose, Git, Java, Maven
  user_data = <<-EOF
              #!/bin/bash
              apt update -y

              # Install Docker
              apt install -y docker.io
              systemctl start docker
              systemctl enable docker
              usermod -aG docker ubuntu

              # Install Git
              apt install -y git

              # Install Maven
              apt install -y maven

              # Install Docker Compose
              apt install -y docker-compose
              EOF
  tags = {
    Name = "MyEC2Instance"
  }
}
# Output Public IP of EC2
output "ec2_public_ip" {
  value = aws_instance.my_ec2.public_ip
}
